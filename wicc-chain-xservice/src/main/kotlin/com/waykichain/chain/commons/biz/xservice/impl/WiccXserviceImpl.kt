package com.waykichain.chain.commons.biz.xservice.impl

import com.alibaba.fastjson.JSON
import com.qiniu.util.Json
import com.waykichain.bet.commons.biz.exception.BizException
import com.waykichain.biz.redis.repository.impl.ValueCacheRedisRepository
import com.waykichain.chain.biz.domain.*
import com.waykichain.chain.biz.domain.QBcWiccTransaction.bcWiccTransaction
import com.waykichain.chain.commons.biz.constant.TaskConstant
import com.waykichain.chain.commons.biz.dict.*
import com.waykichain.chain.commons.biz.env.coin.Environment
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccAlertLogRepository
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccBlockRepository
import com.waykichain.chain.commons.biz.repository.mysql.SysChainMsgNotifySettingRepository
import com.waykichain.chain.commons.biz.service.*
import com.waykichain.chain.commons.biz.utils.WiccUtils
import com.waykichain.chain.commons.biz.xservice.CoinHandler
import com.waykichain.chain.commons.biz.xservice.DingTalkService
import com.waykichain.chain.commons.biz.xservice.TransactionXService
import com.waykichain.chain.commons.biz.xservice.WiccMethodClient
import com.waykichain.chain.dict.SysCoinType
import com.waykichain.chain.dict.TransactionType
import com.waykichain.chain.po.*
import com.waykichain.chain.vo.*
import com.waykichain.coin.wicc.vo.tx.BaseTx
import com.waykichain.chain.vo.v2.AccountTokenInfo
import com.waykichain.chain.vo.v2.CandidateUid
import com.waykichain.chain.vo.v2.VoteFund
import com.waykichain.coin.wicc.po.CreateContractPO
import com.waykichain.coin.wicc.po.CreateContractTxPO
import com.waykichain.coin.wicc.po.SendToAddressWithFeePO
import com.waykichain.coin.wicc.po.SendTxPO
import com.waykichain.coin.wicc.vo.*
import com.waykichain.coin.wicc.vo.tx.BlockRewardTx
import com.waykichain.coin.wicc.vo.tx.UCoinBlockRewardTx
import com.waykichain.commons.base.BizResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.DefaultTransactionDefinition
import java.io.PrintWriter
import java.math.BigDecimal
import java.util.*
import java.util.concurrent.TimeUnit
import java.io.StringWriter




/**
 * Created by Joss on 05/16/18.
 */

@Service("wiccXservice")
open class WiccXserviceImpl : CoinHandler() {


    init {
        regHandler(com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol, this)
    }

    override fun composed() {

    }

    /** Detail exception*/
    override fun getAccountInfoWithError(address: String): BizResponse<AccountInfoVO> {

        var bizResponse = BizResponse<AccountInfoVO>()
        var accountInfoResponse: WiccAccountInfoJsonRpcResponse?
        try {
            accountInfoResponse = wiccMethodClient.getClient().getAccountInfo(address)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getAccountInfo()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        if(accountInfoResponse.result != null) {
            var accountInfoVO = AccountInfoVO()
            accountInfoVO.address = accountInfoResponse.result.address
            accountInfoVO.keyID = accountInfoResponse.result.keyid
            accountInfoVO.minerPKey = accountInfoResponse.result.miner_pubkey
            accountInfoVO.regID = accountInfoResponse.result.regid
            accountInfoVO.postion = accountInfoResponse.result.position
//            accountInfoVO.updateHeight = accountInfoResponse.result.updateHeight
            accountInfoVO.votes = accountInfoResponse.result.received_votes
            accountInfoVO.publicKey = accountInfoResponse.result.owner_pubkey
            bizResponse.data = accountInfoVO
            accountInfoResponse.result.tokens?.forEach{
                when (it.key) {
                    CoinType.WICC.symbol -> accountInfoVO.balance = it.value.free_amount.toBigDecimal()
                }
                var accountToken = AccountTokenInfo()
                accountToken.freeAmount = it.value.free_amount
                accountToken.stakedAmount = it.value.staked_amount
                accountToken.frozenAmount = it.value.frozen_amount
                accountInfoVO.tokens[it.key] = accountToken
            }
            accountInfoResponse.result.vote_list?.forEach{
                var voteFund = VoteFund()
                voteFund.voteType = it.vote_type
                voteFund.votedBcoins = it.voted_bcoins
                voteFund.candidateUid = CandidateUid()
                voteFund.candidateUid!!.id = it.candidate_uid.id
                voteFund.candidateUid!!.idType = it.candidate_uid.id_type
                accountInfoVO.votelist.add(voteFund)
            }
        } else {
            if (accountInfoResponse.error != null) {
                bizResponse.msg = accountInfoResponse.error.message
                bizResponse.code = accountInfoResponse.error.code
            } else {
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }

        return bizResponse

    }

    override fun getAccountInfo(address: String): AccountInfoVO? {
        var accountInfoResponse = wiccMethodClient.getClient().getAccountInfo(address)
        var accountInfoVO = AccountInfoVO()
        if(accountInfoResponse.result != null) {
            accountInfoVO.address = accountInfoResponse.result.address
            accountInfoResponse.result.tokens?.forEach{
                when (it.key) {
                    CoinType.WICC.symbol -> accountInfoVO.balance = it.value.free_amount.toBigDecimal()
                }
                var accountToken = AccountTokenInfo()
                accountToken.freeAmount = it.value.free_amount
                accountToken.stakedAmount = it.value.staked_amount
                accountToken.frozenAmount = it.value.frozen_amount
                accountInfoVO.tokens[it.key] = accountToken
            }
            accountInfoVO.keyID = accountInfoResponse.result.keyid
            accountInfoVO.minerPKey = accountInfoResponse.result.miner_pubkey
            accountInfoVO.regID = accountInfoResponse.result.regid
            accountInfoVO.postion = accountInfoResponse.result.position
//            accountInfoVO.updateHeight = accountInfoResponse.result.updateHeight
            accountInfoVO.votes = accountInfoResponse.result.received_votes
            accountInfoVO.publicKey = accountInfoResponse.result.owner_pubkey
        } else {

        }

        return accountInfoVO

    }

    override fun sendContractTx(contractTransactionPO : ContractTransactionPO): String? {
        val bcWiccContractInfo = bcWiccContractInfoService.getByAddress(
                contractTransactionPO.contractAddress,
                contractTransactionPO.contractAdminType!!)
        var createContractTxPO = CreateContractTxPO()
        createContractTxPO.appId = bcWiccContractInfo.contractAddressRegId
        createContractTxPO.amount = contractTransactionPO.amount
        createContractTxPO.fee = contractTransactionPO.fee
        createContractTxPO.contract = contractTransactionPO.parameter
        createContractTxPO.userregid = bcWiccContractInfo.adminAddress

        var bcWiccSendTransactionLog = BcWiccSendTransactionLog()
        bcWiccSendTransactionLog.transFee = contractTransactionPO!!.fee
        bcWiccSendTransactionLog.amount = contractTransactionPO.amount!!.plus( bcWiccSendTransactionLog.transFee)
        bcWiccSendTransactionLog.transAmount =bcWiccSendTransactionLog.amount

        bcWiccSendTransactionLog.recvAddress = contractTransactionPO.contractAddress
        bcWiccSendTransactionLog.requestUuid = contractTransactionPO.requestUUID
        bcWiccSendTransactionLog.prameter = contractTransactionPO.parameter
        bcWiccSendTransactionLog.status = 100
        bcWiccSendTransactionLog = bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)

        var reponse: WiccCreateContractTxJsonRpcResponse?
        try {
            reponse = wiccMethodClient.getClient().WiccCreateContractTx(createContractTxPO)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] WiccCreateContractTx()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        if(reponse.error != null) {
            bcWiccSendTransactionLog.remark = "[${reponse.error.code}],${reponse.error.message} "
            bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
            throw BizException(reponse.error.code, reponse.error.message)

        }
        bcWiccSendTransactionLog.txid = reponse.result.txid
        bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
        return bcWiccSendTransactionLog.txid
    }

    override fun getBalance(address: String?): BizResponse<BalanceVO> {
        var response: WiccAccountInfoJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().getAccountInfo(address)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getAccountInfo()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<BalanceVO>()
        if (response.result != null) {
            var balanceVO = BalanceVO()
            response.result.tokens?.forEach{
                when (it.key) {
                    CoinType.WICC.symbol -> balanceVO.balance = it.value.free_amount.toBigDecimal()
                    CoinType.WUSD.symbol -> balanceVO.balancewusd = it.value.free_amount.toBigDecimal()
                }
                var accountToken = AccountTokenInfo()
                accountToken.freeAmount = it.value.free_amount
                accountToken.stakedAmount = it.value.staked_amount
                accountToken.frozenAmount = it.value.frozen_amount
                balanceVO.tokens[it.key] = accountToken
            }
//            balanceVO.number = response.result.updateHeight
            bizResponse.data = balanceVO
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

    override fun getBalanceByLog(address: String?): BalanceVO? {

        val lastLogInfo = bcWiccWalletAccountLogService.getLastLogInfo(address)
        var lastBlockId = bcWiccBlockService.getLastBlockId()
        var balanceVO = BalanceVO()
        if(lastLogInfo != null) {
            balanceVO.number = lastBlockId
            balanceVO.balance = lastLogInfo!!.afterBalance!!.toBigDecimal()
        } else {
            balanceVO.number = lastBlockId
            balanceVO.balance = BigDecimal.ZERO
        }
        return balanceVO
    }

    override fun getTxidInfo(queryTxidInfoPO: QueryTxidInfoPO): TxidVO? {
        val bcWiccTransaction = bcWiccTransactionService.getByTxid(queryTxidInfoPO.txid!!)

        var txidVO = TxidVO()
        txidVO.symbol = com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol
        txidVO.txid = queryTxidInfoPO.txid
        txidVO.number = bcWiccTransaction!!.blockNumber
        return txidVO
    }

    override fun getChainTxidInfo(txid: String): BizResponse<TxidDetailVO> {

        var baseTx: BaseTx?
        try {
            baseTx = transactionXService.getTxDetail(txid)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getTxDetail()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<TxidDetailVO>()
        if (baseTx != null) {
            var txidDetailVO = TxidDetailVO()
            txidDetailVO.sendAddress = baseTx.from_addr
            txidDetailVO.fee = baseTx.fees.toBigDecimal()
            txidDetailVO.amount = BigDecimal(baseTx.coin_amount ?: 0)
            txidDetailVO.coinSymbol = baseTx.coin_symbol
            txidDetailVO.feeSymbol = baseTx.fee_symbol
            txidDetailVO.toAddress = baseTx.to_addr
            txidDetailVO.regId = baseTx.tx_uid
            txidDetailVO.txid = baseTx.txid
            txidDetailVO.contract = baseTx.arguments
            txidDetailVO.txType = baseTx.tx_type
            txidDetailVO.arguments =baseTx.arguments

            bizResponse.data = txidDetailVO
        } else {
            bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
            bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
        }

        return bizResponse

    }

    /**
     * 根据交易hash查询交易详细信息
     */
    override fun getChainTxidDetailInfo(txid: String): BizResponse<TxidDetailInfoVO> {

        var response: BaseTx?
        try {
            response = transactionXService.getTxDetail(txid)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getTxDetail()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<TxidDetailInfoVO>()
        if (response != null) {
            val txDetailInfoVO = TxidDetailInfoVO()
            txDetailInfoVO.addr = response.from_addr
            txDetailInfoVO.txtype = response.tx_type
            txDetailInfoVO.money = response.coin_amount.toBigDecimal()
            txDetailInfoVO.fees = response.fees.toBigDecimal()
            txDetailInfoVO.desaddr = response.to_addr
            txDetailInfoVO.regid = response.tx_uid
            txDetailInfoVO.hash = response.txid
            txDetailInfoVO.height = response.valid_height
            txDetailInfoVO.blockhash = response.block_hash
            txDetailInfoVO.confirmHeight = response.confirmed_height
            txDetailInfoVO.confirmedtime = response.confirmed_time
            txDetailInfoVO.contract = response.arguments
            txDetailInfoVO.rawtx = response.rawtx
            bizResponse.data = txDetailInfoVO
        } else {
            bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
            bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
        }

        return bizResponse
    }


    @Transactional
    override fun sendTransaction(coinSendTransactionPO: CoinSendTransactionPO): String? {
        var bcWiccSendTransactionLog = BcWiccSendTransactionLog()
        bcWiccSendTransactionLog.amount = coinSendTransactionPO.amount!!.toLong()
        bcWiccSendTransactionLog.transFee = coinSendTransactionPO.fee!!.toLong()
        bcWiccSendTransactionLog.transAmount =bcWiccSendTransactionLog.amount - bcWiccSendTransactionLog.transFee

        bcWiccSendTransactionLog.sendAddress = coinSendTransactionPO.sendAddress
        bcWiccSendTransactionLog.recvAddress = coinSendTransactionPO.recvAddress
        bcWiccSendTransactionLog.requestUuid = coinSendTransactionPO.requestUUID
        bcWiccSendTransactionLog = bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)


        var sendToAddressWithFeePO = SendTxPO()
        sendToAddressWithFeePO.sendAddress = bcWiccSendTransactionLog.sendAddress
        sendToAddressWithFeePO.receiveAddress = bcWiccSendTransactionLog.recvAddress
        sendToAddressWithFeePO.amount = coinSendTransactionPO.amount
        sendToAddressWithFeePO.fee =  coinSendTransactionPO.fee?: BigDecimal(TransactionConstantDict.SENDTOADDRESS_DEFAULT_FEE.value)
        var reponse: WiccSendToAddressWithFeeJsonRpcResponse?
        try {
            reponse = wiccMethodClient.getClient().sendToAddressWithFee(sendToAddressWithFeePO)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] sendToAddressWithFee()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        if(reponse.error != null) {
            bcWiccSendTransactionLog.remark = "[${reponse.error.code}],${reponse.error.message} "
            bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
            throw BizException(reponse.error.code, reponse.error.message)

        }
        bcWiccSendTransactionLog.txid = reponse.result.hash
        bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
        return bcWiccSendTransactionLog.txid
    }

    override fun genAddress(addressType:Int?): CoinAddressVO {
        var coinAddressVO = CoinAddressVO()
        var reponse: WiccAddressJsonRpcResponse?
        try {
            reponse = wiccMethodClient.getClient().newAddress
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] newAddress", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        if(reponse.error != null) {
            throw BizException(reponse.error.code, reponse.error.message)
        }

        coinAddressVO.address = reponse.result.addr
        coinAddressVO.symbol = com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol

        var bcWiccWalletAddress = BcWiccWalletAddress()
        bcWiccWalletAddress.address = coinAddressVO.address
        bcWiccWalletAddress.walletId = 1
        bcWiccWalletAddressService.save(bcWiccWalletAddress)
        return coinAddressVO
    }

    override fun getTransactions(queryTransactionPO: QueryTransactionPO): TransactionsVO {
        if (null == queryTransactionPO.address || BLANK.equals(queryTransactionPO.address?.trim()) || (null != queryTransactionPO.startNumber && queryTransactionPO.startNumber!! < 0)) {
            throw BizException(ErrorCode.PARAM_ERROR)
        }

        var transactionsVO = TransactionsVO()

        transactionsVO.currentHeight = bcWiccBlockService.getLastBlockId()
        var cond = com.waykichain.chain.commons.biz.cond.QueryTransactionCond()
        cond.address = queryTransactionPO.address
        cond.startNumber = queryTransactionPO.startNumber
        val transactionList = bcWiccTransactionService.getByAddress(cond)
        val data = arrayListOf<TransactionVO>()
        if (null != transactionList && transactionList.isNotEmpty()) {
            for (bean in transactionList) {
                val transaction = TransactionVO()
                if (null != bean.money) {
                   // transaction.amount = BigDecimal(bean.money / Math.pow(10.0, 8.0).toLong())
                    transaction.amount = BigDecimal(bean.money)
                }
                transaction.confirmedHeight = bean.confirmHeight
                transaction.srcAddress = bean.addr
                transaction.desAddress = bean.desaddr
                transaction.symbol = CoinType.WICC.msg
                transaction.tx = bean.txid
                transaction.coinsymbol = bean.coinSymbol
                transaction.feesymbol = bean.feeSymbol
                transaction.confirmedTime = Date(bean.confirmedTime)
                if (null != bean.fees) {
                    transaction.fees = BigDecimal(bean.fees)
                }
                transaction.contract = bean.contract
                transaction.srcRegId = bean.regid
                transaction.desRegId = bean.descregid
                transaction.height = bean.height
                if (null != bean.txType) {
                    transaction.txType = TransactionType.parseTransactionTypeToCode(bean.txType)
                }
                data.add(transaction)
            }
        }
        transactionsVO.transactions = data
        return transactionsVO
    }

    override fun scanBlock() {

        logger.info( "url info:${Environment.WICC_HOST_IP}, ${Environment.WICC_HOST_PORT}")

        /**获取数据库当前最新block*/
        var lastBlockId= bcWiccBlockService.getLastBlockId()

        /**获取最新链上最新block*/
        var currentBcNumber = wiccMethodClient.getClient().info.result.tipblock_height

        val coin = coinService.getBySymbol(com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol)

        valueCacheRedisRepository.put(
                "SYS_WICC_CHAIN_CURRENT_HEIGHT", currentBcNumber.toString(),
                60*60,
                TimeUnit.SECONDS)

        if(coin!!.startNumber  > lastBlockId!!)
            lastBlockId = coin!!.startNumber

        var dbTailNumber =  currentBcNumber - coin.miniConfirmCount

        ((lastBlockId!!.plus(1))!!..dbTailNumber).forEach {
            logger.info( "add new block, blockHeight=$it")
            addNewBlock(it)
        }
    }

    override fun addNewBlock(blockId:Int) {
        val response = wiccMethodClient.getClient().getBlock(blockId)

        var bcWiccBlock = BcWiccBlock()
        response.result.let {
            bcWiccBlock.number = it.height
            bcWiccBlock.hash = it.block_hash
            bcWiccBlock.confirmations = it.confirmations
            bcWiccBlock.size = it.size
            bcWiccBlock.height = it.height
            bcWiccBlock.version = it.version
            bcWiccBlock.merkleRoot = it.merkle_root
            bcWiccBlock.time = Date(it.time * 1000)
            bcWiccBlock.chainwork = it.chainwork
            bcWiccBlock.nonce = it.nonce
            bcWiccBlock.fuel = it.fuel
            bcWiccBlock.fuelRate = it.fuelrate
            bcWiccBlock.previousBlockHash = it.previous_block_hash
            bcWiccBlock.nextBlockHash = it.next_block_hash
        }

        var txdetails = updateBlockTx(response.result.tx)

        updateBlockInfo(bcWiccBlock, txdetails)
    }

    private fun updateBlockTx(txs: List<String>): ArrayList<BaseTx> {
        var txdetails = ArrayList<BaseTx>()

        for (txid in txs) {
            val txdetail = transactionXService.getTxDetail(txid)?: continue

            logger.info("txdetail:" + Json.encode(txdetail))

            updateAccount(txdetail)

            txdetails.add(txdetail)
        }
        return txdetails
    }

    private  fun updateAccount(txdetail: BaseTx) {

        if (txdetail.tx_type == TransactionType.BCOIN_TRANSFER_TX.type || txdetail.tx_type == TransactionType.BCOIN_TRANSFER_TX.type
                || txdetail.tx_type == TransactionType.UCOIN_TRANSFER_TX.type || txdetail.tx_type == TransactionType.LCONTRACT_DEPLOY_TX.type ||
                txdetail.tx_type == TransactionType.LCONTRACT_INVOKE_TX.type ) {

            var money = txdetail.coin_amount
            if (txdetail.coin_symbol == SysCoinType.WICC.code && money == 0L) money = txdetail.coin_amount
            ///< 减小起转账人的余额
            bcWiccWalletAccountService.addBalance(
                    txdetail.from_addr,
                    SysCoinType.WICC.code,
                    0L.minus(money),
                    0L.minus(txdetail.fees),
                    txdetail.tx_type,
                    txdetail.txid,
                    txdetail.confirmed_height,
                    WiccUtils.getGmt0(txdetail.confirmed_time),
                    null)
        }

        if (txdetail.tx_type == TransactionType.BCOIN_TRANSFER_TX.type ||  txdetail.tx_type == TransactionType.UCOIN_TRANSFER_TX.type ) {
            bcWiccWalletAccountService.addBalance(
                    txdetail.to_addr!!,
                    SysCoinType.WICC.code,
                    txdetail.coin_amount,
                    0,
                    txdetail.tx_type,
                    txdetail.txid,
                    txdetail.confirmed_height,
                    WiccUtils.getGmt0(txdetail.confirmed_time),
                    null)
        }

        if (txdetail.tx_type == TransactionType.BLOCK_REWARD_TX.type) {
            val blockRewardTx: BlockRewardTx = txdetail as BlockRewardTx
            //<旷工奖励
            if (blockRewardTx.reward_fees.compareTo(0L) != 0) {
                bcWiccWalletAccountService.addBalance(
                        txdetail.from_addr,
                        SysCoinType.WICC.code,
                        blockRewardTx.reward_fees,
                        0,
                        txdetail.tx_type,
                        txdetail.txid,
                        txdetail.confirmed_height,
                        WiccUtils.getGmt0(txdetail.confirmed_time),
                        null)
            }
        }

        if ( txdetail.tx_type == TransactionType.UCOIN_BLOCK_REWARD_TX.type) {
            val uCoinBlockRewardTx: UCoinBlockRewardTx = txdetail as UCoinBlockRewardTx
            //<旷工奖励
            if (uCoinBlockRewardTx.reward_fees != null && uCoinBlockRewardTx.reward_fees!!.WICC.compareTo(0L) != 0) {

                bcWiccWalletAccountService.addBalance(
                        txdetail.from_addr,
                        SysCoinType.WICC.code,
                        uCoinBlockRewardTx.reward_fees!!.WICC?:0L,
                        0,
                        txdetail.tx_type,
                        txdetail.txid,
                        txdetail.confirmed_height,
                        WiccUtils.getGmt0(txdetail.confirmed_time),
                        null)
            }
        }


//            if (txdetail.tx_type == TransactionType.LCONTRACT_DEPLOY_TX.type) {
//                ///< 解析合约
//            }
    }


    override fun submitOfflineTransaction(coinOfflineTransactionPO: CoinOfflineTransactionPO): String? {
        var bcWiccOfflineTransacationLog = BcWiccOfflineTransacationLog()
        bcWiccOfflineTransacationLog.requestUuid  = coinOfflineTransactionPO.requestUuid
        bcWiccOfflineTransacationLog.info = coinOfflineTransactionPO.offlineTransactionInfo
        bcWiccOfflineTransacationLog = bcWiccOfflineTransacationLogService.save(bcWiccOfflineTransacationLog)


        var reponse: WiccSubmitTxJsonRpcResponse?
        try {
            reponse = wiccMethodClient.getClient().submitTx(coinOfflineTransactionPO.offlineTransactionInfo!!)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] submitTx()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        if(reponse.error != null) {
            bcWiccOfflineTransacationLog.remark = "[${reponse.error.code}],${reponse.error.message} "
            bcWiccOfflineTransacationLogService.save(bcWiccOfflineTransacationLog)
            throw BizException(reponse.error.code, reponse.error.message)

        }
        bcWiccOfflineTransacationLog.txid = reponse.result.txid
        bcWiccOfflineTransacationLogService.save(bcWiccOfflineTransacationLog)
        return bcWiccOfflineTransacationLog.txid
    }

    override fun getChainInfo(): WiccInfoJsonRpcResponse? {
        return wiccMethodClient.getClient().info
    }

    @Transactional
    open fun updateBlockInfo(bcWiccBlock: BcWiccBlock, txdetails:List<BaseTx>) {
        ///<Save BcBtcBlock
        ///<Save Transactions
        var bcWiccTransactions = ArrayList<BcWiccTransaction>()
        txdetails.forEach{
            bcWiccTransactions.add(transactionXService.tranferTxDetailForDB(it))
        }
        try {
            bcWiccBlockService.save(bcWiccBlock)
            bcWiccTransactionService.save(bcWiccTransactions)

        } catch (e:Exception) {
            e.printStackTrace()
            throw e
        }
    }

    /**
     * 激活账户
     */
    override fun registerAccountTx(queryAccountInfoPO: QueryAccountInfoPO): String? {
        var response: WiccSubmitTxJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().activeAddress(queryAccountInfoPO.address)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] activeAddress()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        if(response.error != null) {
            throw BizException(response.error.code, response.error.message)
        }

        return response.result.txid
    }


    override fun createContract(createContractPO: CreateContractPO): String? {

        var response: WiccTxHashJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().createContract(createContractPO)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] createContract()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        if (response.error != null)  {
            throw BizException(response.error.code, response.error.message)
        }

        return response.result.txid
    }

    override fun getBlockCount(): Long? {
        var response: WiccBlockCountJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().getBlockCount()
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getBlockCount()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        if (response.error != null) {
            throw BizException(response.error.code, response.error.message);
        }

        return response.result;
    }

    override fun getBlockByHeight(height: Int): BcWiccBlock{
        var response = wiccMethodClient.getClient().getBlock(height)
        var bcWiccBlock = BcWiccBlock()
        response.result.let {
            bcWiccBlock.number = it.height
            bcWiccBlock.hash = it.block_hash
            bcWiccBlock.confirmations = it.confirmations
            bcWiccBlock.size = it.size
            bcWiccBlock.height = it.height
            bcWiccBlock.version = it.version
            bcWiccBlock.merkleRoot = it.merkle_root
            bcWiccBlock.time = Date(it.time * 1000)
            bcWiccBlock.chainwork = it.chainwork
            bcWiccBlock.nonce = it.nonce
            bcWiccBlock.fuel = it.fuel
            bcWiccBlock.fuelRate = it.fuelrate
            bcWiccBlock.previousBlockHash = it.previous_block_hash
            bcWiccBlock.nextBlockHash = it.next_block_hash
        }
        return bcWiccBlock
    }

    override fun getBlockHash(blockId:Int): String? {
        val response = wiccMethodClient.getClient().getBlockHash(blockId)
        if (response.error !=null) {
            throw BizException(response.error.code, response.error.message)
        }

        return response.result.txid
    }

    override fun getTotalCoin(): BizResponse<BigDecimal> {

        var response: WiccTotalCoinJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().getTotalCoin()
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getTotalCoin()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<BigDecimal>()
        if (response.result != null) {
            bizResponse.data = response.result.total_coins
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
     * 链分叉-数据回滚
     */
    override fun checkRollback(){

        /** 保存错误数据*/
        recordErrorData()

        /** 处理错误数据*/
        val rollbackBlockList = rollbackBlockBakService.queryByStatus(RollbackBlockBakTypeDict.UN_PROCESSED.code)
        if (rollbackBlockList == null || rollbackBlockList.none()) {
            logger.info("[scan chain rollback] No data need to dealed ")
            return
        }

        for(rollBackBlock in rollbackBlockList!!){

            val status = txManager!!.getTransaction(DefaultTransactionDefinition())
            try {
                val response = wiccMethodClient.getClient().getBlock(rollBackBlock.height)
                if (response.result == null) {
                    logger.info("[scan chain rollback] get block failed. bolockNumber=${rollBackBlock.height} ")
                    continue
                }
                if(rollBackBlock.hash != response.result.block_hash) {

                    /** -交易信息*/
                    val transactionList = bcWiccTransactionService.getByBlockNumber(rollBackBlock.height)
                    if (transactionList == null || transactionList.isEmpty()) {
                        logger.info("[scan chain rollback] There is no transaction information in this block. bolockNumber=${rollBackBlock.height} ,msg=${response.error}")
                    } else {
                        /** --处理旧交易数据*/
                        dealOldTransactionData(transactionList)
                        /** --同步链上交易信息*/
                        var bcWiccTransactioins = arrayListOf<BcWiccTransaction>()
                        response.result.tx.forEach {
                            syncTransactionAgain(it,bcWiccTransactioins,rollBackBlock.number)
                        }
                        bcWiccTransactionService.save(bcWiccTransactioins)
                    }

                    /** -修改区块信息*/
                    var oldBlock = bcWiccBlockService.getByHeight(rollBackBlock.height)
                    oldBlock?.let {
                        it.number = response.result.height
                        it.hash = response.result.block_hash
                        it.confirmations = response.result.confirmations
                        it.size = response.result.size
                        it.height = response.result.height
                        it.version = response.result.version
                        it.merkleRoot = response.result.merkle_root
                        it.time = Date(response.result.time * 1000)
                        it.chainwork = response.result.chainwork
                        it.nonce = response.result.nonce
                        it.fuel = response.result.fuel
                        it.fuelRate = response.result.fuelrate
                        it.previousBlockHash = response.result.previous_block_hash
                        it.nextBlockHash = response.result.next_block_hash
                        bcWiccBlockService.save(it)
                    }

                    /** -修改相邻区块信息*/
                    var previousBlock = bcWiccBlockService.getByHeight(rollBackBlock.height - 1)
                    if (previousBlock != null) {
                        previousBlock.nextBlockHash = response.result.block_hash
                        bcWiccBlockService.save(previousBlock)
                    }
                    var nextBlock = bcWiccBlockService.getByHeight(rollBackBlock.height + 1)
                    if (nextBlock != null) {
                        nextBlock.previousBlockHash = response.result.block_hash
                        bcWiccBlockService.save(nextBlock)
                    }

                    /** -修改记录表状态*/
                    rollBackBlock.status = RollbackBlockBakTypeDict.PROCESSED.code
                    rollbackBlockBakService.save(rollBackBlock)

                    txManager.commit(status)
                } else{
                    logger.info("[scan chain rollback] This block is already on the chain. bolockNumber=${rollBackBlock.number} ")
                    txManager.rollback(status)
                }
            } catch (e: Exception) {
                logger.error("[scan chain rollback] error. bolockNumber=${rollBackBlock.number} ", e)
            }
        }

    }

    private fun recordErrorData() {

        var sendUrl:String? = null
        var chainMsgNotifySettingList = sysChainMsgNotifySettingRepository.findAll(
                QSysChainMsgNotifySetting.sysChainMsgNotifySetting.msgId.eq(NotifyMessageIdDict.ROLLBACK_NOTIFY.code))
        if( chainMsgNotifySettingList != null && chainMsgNotifySettingList.count() !=  0) {
            sendUrl = chainMsgNotifySettingList.first()!!.msgUrl!!
        }

        val handler = CoinHandler.getHandler(com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol)
        val currentBlockcount:Int? = handler!!.getBlockCount()!!.toInt()
        val checkBlockcount:Int = 1 * 60 * 60 / 10
        var lastCheckBlockcount = currentBlockcount!! - checkBlockcount

        var blockList = bcWiccBlockRepository.findAll(QBcWiccBlock.bcWiccBlock.number.gt(lastCheckBlockcount))

        if (blockList == null || blockList.none()) {
            logger.info("[scan chain rollback] Record error data, no error data")
        }

        var rollBackBlockBakList = arrayListOf<RollbackBlockBak>()
        for(block in blockList){

            try {
                val response = wiccMethodClient.getClient().getBlockHash(block.height)
                if (response.result == null) {
                    logger.info("[scan chain rollback] Record error data, getBlockHash() Error. bolockNumber=${block.height} ")
                    continue
                }
                if (block.hash != response.result.txid) {

                    var rollbackBlockBak = RollbackBlockBak()
                    BeanUtils.copyProperties(block, rollbackBlockBak)
                    rollbackBlockBak.id = null
                    rollbackBlockBak.createdAt = null
                    rollbackBlockBak.updatedAt = null
                    if ( rollbackBlockBakService.queryByHeightAndStatus(block.height, RollbackBlockBakTypeDict.UN_PROCESSED.code) == null) rollBackBlockBakList.add(rollbackBlockBak)
                    try {
                        /** 错误日志*/
                        var message = "Roll back check notify : the block height:" + block.height + " refer to scan chain database the blockhash is " + block.hash + " but it refer to WaykiChain blockhash is ${block.hash}"
                        logger.info(message)
                        dingTalkService.sendTextMessage(sendUrl!!, message)
                        var bcWiccAlertLog = BcWiccAlertLog()
                        bcWiccAlertLog.address = block.height.toString()
                        bcWiccAlertLog.alertInfo = message
                        bcWiccAlertLog.notifyLinkUrl=  sendUrl
                        bcWiccAlertLog.alertType = BcWiccAlertLogType.CHAIN_ROOLBACK.num
                        bcWiccAlertLogRepository.save(bcWiccAlertLog)
                    } catch (e: Exception) {
                        logger.error("[scan chain rollback] Record error data, dingTalk error. bolockNumber=${block.height} ", e)
                    }


                }
            } catch (e: Exception) {
                logger.error("[scan chain rollback] Record error data, Error. bolockNumber=${block.height} ", e)
                continue
            }

        }
        /** 保存需要更新的区块信息*/
        if (rollBackBlockBakList.isNotEmpty()) {
            rollbackBlockBakService.save(rollBackBlockBakList)
            logger.info("[scan chain rollback] Record error data, number=${rollBackBlockBakList.size}")
        }


    }


    /**
     * 处理旧交易数据
     */
    private fun dealOldTransactionData(transactionList: List<BcWiccTransaction>){

        for (tran in transactionList) {
            logger.info("[scan chain rollback] deal old data, transaction=${Json.encode(tran)}")
            //备份交易信息
            val rollbackTransactionBak = RollbackTransactionBak()
            BeanUtils.copyProperties(tran, rollbackTransactionBak)
            rollbackTransactionBak.id = null
            rollbackTransactionBak.createdAt = null
            rollbackTransactionBak.updatedAt = null
            rollbackTransactionBakService.save(rollbackTransactionBak)

            //修改账户信息
            if(tran.txType == TransactionType.BCOIN_TRANSFER_TX.type ||
                    tran.txType == TransactionType.BCOIN_TRANSFER_TX.type||
                    tran.txType == TransactionType.LCONTRACT_DEPLOY_TX.type ||
                    tran.txType == TransactionType.ACCOUNT_REGISTER_TX.type ||
                    tran.txType == TransactionType.LCONTRACT_INVOKE_TX.type) {

                //增加起转账人的余额
                bcWiccWalletAccountService.addBalance(
                        tran.addr,
                        com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol,
                        tran.money?:0L,
                        tran.fees,
                        tran.txType,
                        tran.txid,
                        tran.confirmHeight,
                        WiccUtils.getGmt0(tran.confirmedTime),
                        "rollback")
            }

            if(tran.txType == TransactionType.BCOIN_TRANSFER_TX.type || tran.txType == TransactionType.BCOIN_TRANSFER_TX.type ) {
                bcWiccWalletAccountService.addBalance(
                        tran.desaddr,
                        com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol,
                        0L.minus(tran.money),
                        0,
                        tran.txType,
                        tran.txid,
                        tran.confirmHeight,
                        WiccUtils.getGmt0(tran.confirmedTime),
                        "rollback")
            }

            if(tran.txType == TransactionType.BLOCK_REWARD_TX.type || tran.txType == TransactionType.UCOIN_BLOCK_REWARD_TX.type ){
                //<旷工奖励
                bcWiccWalletAccountService.addBalance(
                        tran.addr,
                        com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol,
                        0L.minus(tran.money),
                        0,
                        tran.txType,
                        tran.txid,
                        tran.confirmHeight,
                        WiccUtils.getGmt0(tran.confirmedTime),
                        "rollback")
            }

            if(tran.txType == TransactionType.LCONTRACT_DEPLOY_TX.type) {
                ///< 解析合约
            }

        }
        //删除交易信息
        bcWiccTransactionService.deleteBatch(transactionList)
    }

    /**
     * 同步链上交易信息
     */
    private fun syncTransactionAgain(hash: String, bcWiccTransactions: MutableList<BcWiccTransaction>, height: Int) {

        val txdetail = transactionXService.getTxDetail(hash)
        logger.info("[scan chain rollback] Synchronize data from chain, transaction=${Json.encode(txdetail)}")
        txdetail?.let {
            bcWiccTransactions.add(transactionXService.tranferTxDetailForDB(txdetail))
            updateAccount(txdetail)
        }
    }


    /**
     *  check chain stop sync
     */
    override fun checkChainStop(): Boolean {

        /** 错误日志*/
        var message: String? = null
        var chainHeightNow: Long? = null
        try {
            val response = wiccMethodClient.getClient().getBlockCount()
            if (response.result == null) {
                logger.error("[Chain node stop check] getBlockCount error! code=${response.error?.code},messgae=${response.error?.message}")
                message = "Chain node stop notify : get block error!  code=${response.error?.code},messgae=${response.error?.message}"
            } else {
                chainHeightNow = response.result
                val chainHeightLast  = valueCacheRedisRepository.get(TaskConstant.LAST_CHAIN_HEIGHT_REDIS_KEY, Long::class.java)
                logger.info("[Chain node stop check] chainHeightLast=$chainHeightLast, chainHeightNow=$chainHeightNow")
                if (chainHeightLast == chainHeightNow) {
                    message = "Chain node stop notify : The node may have stopped syncing, block height : $chainHeightNow"
                } else {
                    valueCacheRedisRepository.put(TaskConstant.LAST_CHAIN_HEIGHT_REDIS_KEY, chainHeightNow.toString())
                }
            }
        } catch (e: Exception) {
            logger.error("[Chain node stop check] get block error!", e)
            val sw = StringWriter()
            var pw = PrintWriter(sw)
            message = "Chain node stop  notify : This node may be dead,get block height error!  ${sw}"
        }

        if (message != null) {
            /** DingTalk*/
            var chainMsgNotifySettingList = sysChainMsgNotifySettingRepository.findAll(QSysChainMsgNotifySetting.sysChainMsgNotifySetting.msgId.eq(NotifyMessageIdDict.CHAIN_SYNC_STOP_NOTIFY.code))
            var sendUrl:String? = if( chainMsgNotifySettingList != null && !chainMsgNotifySettingList.none()) chainMsgNotifySettingList.first().msgUrl else null
            logger.info(message)
            if (sendUrl != null) dingTalkService.sendTextMessage(sendUrl!!, message)
            var bcWiccAlertLog = BcWiccAlertLog()
            bcWiccAlertLog.address = chainHeightNow?.toString()
            bcWiccAlertLog.alertInfo = message
            bcWiccAlertLog.notifyLinkUrl=  sendUrl
            bcWiccAlertLog.alertType = BcWiccAlertLogType.CHAIN_ROOLBACK.num
            bcWiccAlertLogRepository.save(bcWiccAlertLog)
        }
        return true
    }



    private val BLANK = ""
    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired lateinit var wiccMethodClient: WiccMethodClient
    @Autowired lateinit var bcWiccBlockService: BcWiccBlockService
    @Autowired lateinit var bcWiccTransactionService: BcWiccTransactionService
    @Autowired lateinit var coinService: CoinService
    @Autowired lateinit var bcWiccSendTransactionLogService: BcWiccSendTransactionLogService
    @Autowired lateinit var bcWiccWalletAddressService:BcWiccWalletAddressService
    @Autowired lateinit var bcWiccWalletAccountService: BcWiccWalletAccountService
    @Autowired lateinit var bcWiccOfflineTransacationLogService:BcWiccOfflineTransacationLogService
    @Autowired lateinit var bcWiccContractInfoService:BcWiccContractInfoService
    @Autowired lateinit var bcWiccWalletAccountLogService:BcWiccWalletAccountLogService
    @Autowired lateinit var valueCacheRedisRepository: ValueCacheRedisRepository


    @Autowired lateinit var sysChainMsgNotifySettingRepository: SysChainMsgNotifySettingRepository
    @Autowired lateinit var dingTalkService: DingTalkService
    @Autowired lateinit var bcWiccBlockRepository: BcWiccBlockRepository
    @Autowired lateinit var bcWiccAlertLogRepository: BcWiccAlertLogRepository
    @Autowired lateinit var rollbackBlockBakService: RollbackBlockBakService
    @Autowired lateinit var rollbackTransactionBakService: RollbackTransactionBakService
    @Autowired lateinit var transactionXService: TransactionXService


    @Autowired
    private val txManager: PlatformTransactionManager? = null

}