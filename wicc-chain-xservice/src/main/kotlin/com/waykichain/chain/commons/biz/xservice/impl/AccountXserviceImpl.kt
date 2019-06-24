package com.waykichain.chain.commons.biz.xservice.impl

import com.waykichain.chain.biz.domain.BcWiccWalletAddress
import com.waykichain.chain.commons.biz.dict.ErrorCode
import com.waykichain.chain.commons.biz.service.BcWiccBlockService
import com.waykichain.chain.commons.biz.service.BcWiccWalletAccountLogService
import com.waykichain.chain.commons.biz.service.BcWiccWalletAddressService
import com.waykichain.chain.commons.biz.xservice.AccountXservice
import com.waykichain.chain.commons.biz.xservice.WiccMethodClient
import com.waykichain.chain.po.v2.*
import com.waykichain.chain.vo.v2.*
import com.waykichain.coin.wicc.po.ImportPrivkeyPO
import com.waykichain.coin.wicc.po.WiccGenRegisterAccountrawPO
import com.waykichain.commons.base.BizResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
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
        val response = wiccMethodClient.getClient().getAccountInfo(po.address)
        if(response.result != null) {
            var accountDetailVO = AccountDetailVO()
            accountDetailVO.address = response.result.Address
            accountDetailVO.balance = response.result.balance
            accountDetailVO.keyid = response.result.keyID
            accountDetailVO.minerpkey = response.result.minerPKey
            accountDetailVO.regid = response.result.regID
            accountDetailVO.position = response.result.position
            accountDetailVO.updateheight = response.result.updateHeight
            accountDetailVO.votes = response.result.votes
            accountDetailVO.publickey = response.result.publicKey
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
        val response = wiccMethodClient.getClient().activeAddress(po.address)
        var bizResponse = BizResponse<RegisterAccountVO>()
        if(response.result != null) {
            var registerAccountVO = RegisterAccountVO()
            registerAccountVO.hash = response.result.hash
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

        val response = wiccMethodClient.getClient().newAddress
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

        val response = wiccMethodClient.getClient().getAccountInfo(po.address)
        var bizResponse = BizResponse<AccountBalanceVO>()
        if(response.result != null) {
            var accountBalanceVO = AccountBalanceVO()
            accountBalanceVO.balance = response.result.balance
            accountBalanceVO.updateHeight = response.result.updateHeight
            accountBalanceVO.regId = response.result.regID
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

        val response = wiccMethodClient.getClient().validateAddrress(po.address)
        var bizResponse = BizResponse<ValidateAddressVO>()
        if(response.result != null) {
            var accountBalanceVO = ValidateAddressVO()
            accountBalanceVO.ret = response.result.isRet
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
    override fun genRegisterAccountraw(po: GenRegisterAccountrawPO): BizResponse<GenRegisterAccountrawVO> {

        val heightResponse = wiccMethodClient.getClient().getBlockCount()
        if (heightResponse == null || heightResponse.result == null) {return BizResponse(ErrorCode.GET_BLOCK_HEIGHT_FAIL.code,ErrorCode.GET_BLOCK_HEIGHT_FAIL.msg) }
        var genRegisterAccountrawPO = WiccGenRegisterAccountrawPO()
        genRegisterAccountrawPO.fee = po.fee
        genRegisterAccountrawPO.height = heightResponse.result
        genRegisterAccountrawPO.publickey = po.publickey
        var bizResponse = BizResponse<GenRegisterAccountrawVO>()
        val response = wiccMethodClient.getClient().genRegisterAccountraw(genRegisterAccountrawPO)
        if(response.result != null) {
            var vo = GenRegisterAccountrawVO()
            vo.rawtx = response.result.rawtx
            bizResponse = BizResponse(vo)
        } else {
            if (response.error != null) {
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                logger.error("genRegisterAccountraw() error, response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        return bizResponse
    }

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

        val response = wiccMethodClient.getClient().importPrivkey(importPrivkeyPO)
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

        val lastLogInfo = bcWiccWalletAccountLogService.getLastLogInfo(po.address)
        var lastBlockId = bcWiccBlockService.getLastBlockId()

        var balanceVO = AccountBalanceVO()
        balanceVO.updateHeight = lastBlockId
        balanceVO.balance = if(lastLogInfo != null) {lastLogInfo!!.afterBalance!!.toBigDecimal()} else {BigDecimal.ZERO}

        val response = wiccMethodClient.getClient().getAccountInfo(po.address)
        var bizResponse = BizResponse<AccountBalanceVO>()
        if(response.result != null) {
            balanceVO.regId = response.result.regID
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
        val response = wiccMethodClient.getClient().getTotalCoin()
        var bizResponse = BizResponse<TotalCoinVO>()
        if(response.result != null) {
            var totalCoinVO = TotalCoinVO()
            totalCoinVO.totalCoin = response.result.totalCoin
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