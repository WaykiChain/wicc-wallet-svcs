package com.waykichain.chain.commons.biz.xservice.impl

import com.waykichain.bet.commons.biz.exception.BizException
import com.waykichain.chain.biz.domain.BcWiccWalletAccountLog
import com.waykichain.chain.biz.domain.BcWiccWalletAddress
import com.waykichain.chain.commons.biz.dict.CoinType
import com.waykichain.chain.commons.biz.dict.ErrorCode
import com.waykichain.chain.commons.biz.service.BcWiccBlockService
import com.waykichain.chain.commons.biz.service.BcWiccWalletAccountLogService
import com.waykichain.chain.commons.biz.service.BcWiccWalletAddressService
import com.waykichain.chain.commons.biz.utils.WiccUtils
import com.waykichain.chain.commons.biz.xservice.AccountXservice
import com.waykichain.chain.commons.biz.xservice.WiccMethodClient
import com.waykichain.chain.po.v2.*
import com.waykichain.chain.vo.v2.*
import com.waykichain.coin.wicc.po.ImportPrivkeyPO
import com.waykichain.coin.wicc.po.WiccGenRegisterAccountrawPO
import com.waykichain.coin.wicc.vo.*
import com.waykichain.commons.base.BizResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.math.BigDecimal

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/4 15:28
 *
 * @Description:    用户地址相关
 *
 */

@Service
class AccountXserviceImpl: AccountXservice {


    /**
     * 获取普通账户或者合约账户地址详情[RPC-getaccountinfo]
     */
    override fun getAccountInfo(po: QueryAccountDetailPO): BizResponse<AccountDetailVO> {

        var bizResponse = BizResponse<AccountDetailVO>()
        /** 请求RPC */
        var response: WiccAccountInfoJsonRpcResponse ?
        try {
            response = wiccMethodClient.getClient().getAccountInfo(po.address)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getAccountInfo()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        if(response.result != null) {
            var accountDetailVO = AccountDetailVO()
            accountDetailVO.address = response.result.address
            accountDetailVO.keyid = response.result.keyid
            accountDetailVO.publickey = response.result.owner_pubkey
            accountDetailVO.regid = response.result.regid
            accountDetailVO.regidmature = response.result.isRegid_mature
            accountDetailVO.nickid = response.result.nickid
            accountDetailVO.position = response.result.position
            accountDetailVO.receivedvotes = response.result.received_votes
            accountDetailVO.minerpkey = response.result.miner_pubkey
            response.result.tokens?.forEach{
                when (it.key) {
                    CoinType.WICC.symbol -> accountDetailVO.balance = it.value.free_amount
                }
                var accountToken = AccountTokenInfo()
                accountToken.freeAmount = it.value.free_amount
                accountToken.stakedAmount = it.value.staked_amount
                accountToken.frozenAmount = it.value.frozen_amount
                accountDetailVO.tokens[it.key] = accountToken
            }
            response.result.vote_list?.forEach{
                var voteFund = VoteFund()
                voteFund.voteType = it.vote_type
                voteFund.votedBcoins = it.voted_bcoins
                voteFund.candidateUid = CandidateUid()
                voteFund.candidateUid!!.id = it.candidate_uid.id
                voteFund.candidateUid!!.idType = it.candidate_uid.id_type
                accountDetailVO.votelist.add(voteFund)
            }
            bizResponse = BizResponse(accountDetailVO)
        } else {
            if (response.error != null) {
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }

        return bizResponse

    }

    /**
     * 激活账户 [RPC-registeraccounttx]
     */
    override fun registerAccountTx(po: QueryAccountDetailPO): BizResponse<RegisterAccountVO> {
        var response: WiccSubmitTxJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().activeAddress(po.address)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] activeAddress()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<RegisterAccountVO>()
        if(response.result != null) {
            var registerAccountVO = RegisterAccountVO()
            registerAccountVO.hash = response.result.txid
            bizResponse = BizResponse(registerAccountVO)
        } else {
            if (response.error != null) {
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        return bizResponse
    }


    /**
     * 创建新地址[RPC-getnewaddress]
     */
    override fun getNewaddress(): BizResponse<NewAddressVO> {

        var response: WiccAddressJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().newAddress
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] newAddress", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<NewAddressVO>()
        if (response.result != null) {
            var newAddressVO = NewAddressVO()
            newAddressVO.addr = response.result.addr

            /** 私钥*/
            val dumpPrivkeyResponse = wiccMethodClient.getClient().dumpPrivkey(newAddressVO.addr)
            dumpPrivkeyResponse.result?.let { newAddressVO.privkey = it.privkey}
            bizResponse = BizResponse(newAddressVO)

            // 记录数据
            var bcWiccWalletAddress = BcWiccWalletAddress()
            bcWiccWalletAddress.address = newAddressVO.addr
            bcWiccWalletAddress.walletId = 1
            bcWiccWalletAddressService.save(bcWiccWalletAddress)
        } else {
            if (response.error != null) {
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        return bizResponse
    }

    /**
     * 获取链账户余额
     */
    override fun getAccountBalance(po: QueryAccountDetailPO): BizResponse<AccountBalanceVO> {

        var response: WiccAccountInfoJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().getAccountInfo(po.address)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getAccountInfo()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<AccountBalanceVO>()
        if(response.result != null) {
            var accountBalanceVO = AccountBalanceVO()
//            accountBalanceVO.balance = response.result.balance
//            accountBalanceVO.updateHeight = response.result.updateHeight
            accountBalanceVO.regId = response.result.regid
            bizResponse = BizResponse(accountBalanceVO)
        } else {
            if (response.error != null) {
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        return bizResponse
    }

    /**
     *  检查普通地址或者合约地址是否有效[RPC-validateaddr]
     */
    override fun validateAddress(po: ValidateAddressPO): BizResponse<ValidateAddressVO> {

        var response: WiccValidateAddrJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().validateAddrress(po.address)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] validateAddrress()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<ValidateAddressVO>()
        if(response.result != null) {
            var accountBalanceVO = ValidateAddressVO()
            accountBalanceVO.ret = response.result.is_valid
            bizResponse = BizResponse(accountBalanceVO)
        } else {
            if (response.error != null) {
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                logger.error("validateAddress() error, response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        return bizResponse
    }

    /**
     * 创建离线激活账户的交易内容
     *
     * [RPC-genregisteraccountraw]
     */
//    override fun genRegisterAccountraw(po: GenRegisterAccountrawPO): BizResponse<GenRegisterAccountrawVO> {
//
//        var heightResponse: WiccBlockCountJsonRpcResponse?
//        try {
//            heightResponse = wiccMethodClient.getClient().getBlockCount()
//        } catch (e: Exception) {
//            logger.error("[JsonRpc request error] getBlockCount()", e)
//            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
//        }
//        if (heightResponse == null || heightResponse.result == null) {return BizResponse(ErrorCode.GET_BLOCK_HEIGHT_FAIL.code,ErrorCode.GET_BLOCK_HEIGHT_FAIL.msg) }
//        var genRegisterAccountrawPO = WiccGenRegisterAccountrawPO()
//        genRegisterAccountrawPO.fee = po.fee
//        genRegisterAccountrawPO.height = heightResponse.result
//        genRegisterAccountrawPO.publickey = po.publickey
//        var bizResponse = BizResponse<GenRegisterAccountrawVO>()
//        var response: WiccGenRegisterAccountrawJsonRpcResponse?
//        try {
//            response = wiccMethodClient.getClient().genRegisterAccountraw(genRegisterAccountrawPO)
//        } catch (e: Exception) {
//            logger.error("[JsonRpc request error] genRegisterAccountraw()", e)
//            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
//        }
//        if(response.result != null) {
//            var vo = GenRegisterAccountrawVO()
//            vo.rawtx = response.result.txid
//            bizResponse = BizResponse(vo)
//        } else {
//            if (response.error != null) {
//                bizResponse.msg = response.error.message
//                bizResponse.code = response.error.code
//            } else {
//                logger.error("genRegisterAccountraw() error, response=${response.toString()}")
//                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
//                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
//            }
//        }
//        return bizResponse
//    }

    /**
     * 将私钥（由dumpprivkey导出）导入钱包
     *
     * [RPC-importprivkey]
     */
    override fun importPrivkey(po: ImportPrivateKeyPO): BizResponse<ImportPrivateKeyVO> {

        var importPrivkeyPO = ImportPrivkeyPO()
        importPrivkeyPO.privkey = po.privkey
//        importPrivkeyPO.label = po.label
//        po.rescan?.let { importPrivkeyPO.isRescan = po.rescan!! }

        var response: WiccImportPrivkeyJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().importPrivkey(importPrivkeyPO)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] importPrivkey()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<ImportPrivateKeyVO>()
        if(response.result != null) {
            var vo = ImportPrivateKeyVO()
            vo.importkeyaddress = response.result.imported_key_address
            bizResponse = BizResponse(vo)
        } else {
            if (response.error != null) {
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                logger.error("importPrivkey() error, response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        return bizResponse
    }


    /**
     * 根据log查询余额
     */
    override fun getBalanceByLog(po: QueryAccountDetailPO): BizResponse<AccountBalanceVO> {

        var lastLogInfo: BcWiccWalletAccountLog?
        try {
            lastLogInfo = bcWiccWalletAccountLogService.getLastLogInfo(po.address)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getLastLogInfo()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var lastBlockId = bcWiccBlockService.getLastBlockId()

        var balanceVO = AccountBalanceVO()
        balanceVO.updateHeight = lastBlockId
        balanceVO.balance = if(lastLogInfo != null) {lastLogInfo!!.afterBalance!!.toBigDecimal()} else {BigDecimal.ZERO}

        var response: WiccAccountInfoJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().getAccountInfo(po.address)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getAccountInfo()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<AccountBalanceVO>()
        if(response.result != null) {
            balanceVO.regId = response.result.regid
            bizResponse = BizResponse(balanceVO)
        } else {
            if (response.error != null) {
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }

        return bizResponse
    }

    /**
     * 获取所有WICC的数量[RPC-gettotalcoin]
     */
    override fun getTotalCoin(): BizResponse<TotalCoinVO> {
        var response: WiccTotalCoinJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().getTotalCoin()
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getTotalCoin()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<TotalCoinVO>()
        if(response.result != null) {
            var totalCoinVO = TotalCoinVO()
            totalCoinVO.totalCoin = response.result.total_coins
            bizResponse = BizResponse(totalCoinVO)
        } else {
            if (response.error != null) {
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        return bizResponse
    }

    private var logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var wiccMethodClient: WiccMethodClient

    @Autowired
    lateinit var bcWiccBlockService: BcWiccBlockService

    @Autowired
    lateinit var bcWiccWalletAddressService: BcWiccWalletAddressService

    @Autowired
    lateinit var bcWiccWalletAccountLogService: BcWiccWalletAccountLogService

}