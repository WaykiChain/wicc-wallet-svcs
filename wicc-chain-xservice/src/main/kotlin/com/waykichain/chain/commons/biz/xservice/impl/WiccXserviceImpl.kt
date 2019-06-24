package com.waykichain.chain.commons.biz.xservice.impl

import com.alibaba.fastjson.JSON
import com.qiniu.util.Json
import com.waykichain.bet.commons.biz.exception.BizException
import com.waykichain.biz.redis.repository.impl.ValueCacheRedisRepository
import com.waykichain.chain.biz.domain.*
import com.waykichain.chain.commons.biz.dict.*
import com.waykichain.chain.commons.biz.env.coin.Environment
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccAlertLogRepository
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccBlockRepository
import com.waykichain.chain.commons.biz.repository.mysql.SysChainMsgNotifySettingRepository
import com.waykichain.chain.commons.biz.service.*
import com.waykichain.chain.commons.biz.utils.WiccUtils
import com.waykichain.chain.commons.biz.xservice.CoinHandler
import com.waykichain.chain.commons.biz.xservice.DingTalkService
import com.waykichain.chain.commons.biz.xservice.WiccMethodClient
import com.waykichain.chain.dict.TransactionType
import com.waykichain.chain.po.*
import com.waykichain.chain.vo.*
import com.waykichain.coin.wicc.po.CreateContractPO
import com.waykichain.coin.wicc.po.CreateContractTxPO
import com.waykichain.coin.wicc.po.SendToAddressPO
import com.waykichain.coin.wicc.po.SendToAddressWithFeePO
import com.waykichain.coin.wicc.vo.WiccGetTxDetailResult
import com.waykichain.coin.wicc.vo.WiccInfoJsonRpcResponse
import org.bouncycastle.crypto.tls.CipherType.block
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.*
import java.util.concurrent.TimeUnit
import org.springframework.transaction.support.DefaultTransactionDefinition
import org.springframework.transaction.PlatformTransactionManager





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

    override fun getAccountInfo(address: String): AccountInfoVO? {
        var accountInfoResponse = wiccMethodClient.getClient().getAccountInfo(address)
        var accountInfoVO = AccountInfoVO()
        if(accountInfoResponse.result != null) {
            accountInfoVO.address = accountInfoResponse.result.Address
            accountInfoVO.balance = accountInfoResponse.result.balance
            accountInfoVO.keyID = accountInfoResponse.result.keyID
            accountInfoVO.minerPKey = accountInfoResponse.result.minerPKey
            accountInfoVO.regID = accountInfoResponse.result.regID
            accountInfoVO.postion = accountInfoResponse.result.position
            accountInfoVO.updateHeight = accountInfoResponse.result.updateHeight
            accountInfoVO.votes = accountInfoResponse.result.votes
            accountInfoVO.publicKey = accountInfoResponse.result.publicKey
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

        var reponse= wiccMethodClient.getClient().WiccCreateContractTx(createContractTxPO)
        if(reponse.error != null) {
            bcWiccSendTransactionLog.remark = "[${reponse.error.code}],${reponse.error.message} "
            bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
            throw BizException(ErrorCode.SYS_INTERNAL_ERROR.code, bcWiccSendTransactionLog.remark)

        }
        bcWiccSendTransactionLog.txid = reponse.result.hash
        bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
        return bcWiccSendTransactionLog.txid
    }

    override fun getBalance(address: String?): BalanceVO? {
        val accountInfo = wiccMethodClient.getClient().getAccountInfo(address)
        var balanceVO = BalanceVO()
        balanceVO.balance = accountInfo.result.balance
        balanceVO.number = accountInfo.result.updateHeight
        return balanceVO
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

    override fun getChainTxidInfo(txid: String): TxidDetailVO {
        var response= wiccMethodClient.getClient().getTxDetail(txid)
        var txidDetailVO = TxidDetailVO()
        txidDetailVO.sendAddress = response.result.addr
        txidDetailVO.fee = response.result.fees.toBigDecimal()
        if(response.result.money == null) {
            txidDetailVO.amount = BigDecimal.ZERO
        } else {
            txidDetailVO.amount = BigDecimal(response.result.money)
        }
        txidDetailVO.toAddress = response.result.desaddr
        txidDetailVO.regId = response.result.regid
        txidDetailVO.txid = response.result.hash
        txidDetailVO.contract = response.result.arguments
        if(txidDetailVO.contract == null)
            txidDetailVO.contract =response.result.arguments

        txidDetailVO.arguments = response.result.arguments
        txidDetailVO.memo = response.result.memo

        txidDetailVO.txType = response.result.txtype
        txidDetailVO.arguments =response.result.arguments
        txidDetailVO.memo = response.result.memo
        return txidDetailVO

    }

    /**
     * 根据交易hash查询交易详细信息
     */
    override fun getChainTxidDetailInfo(txid: String): TxidDetailInfoVO {
        val response= wiccMethodClient.getClient().getTxDetail(txid)
        val txDetailInfoVO = TxidDetailInfoVO()
        txDetailInfoVO.addr = response.result.addr
        txDetailInfoVO.txtype = response.result.txtype
        if(response.result.money == null) {
            txDetailInfoVO.money = BigDecimal.ZERO
        } else {
            txDetailInfoVO.money = BigDecimal(response.result.money)
        }
        if (null == response.result.fees) {
            txDetailInfoVO.fees = BigDecimal.ZERO
        } else {
            txDetailInfoVO.fees = response.result.fees.toBigDecimal()
        }

        txDetailInfoVO.desaddr = response.result.desaddr
        txDetailInfoVO.regid = response.result.regid
        txDetailInfoVO.hash = response.result.hash
        txDetailInfoVO.height = response.result.height
        txDetailInfoVO.blockhash = response.result.blockhash
        txDetailInfoVO.confirmHeight = response.result.confirmedheight
        txDetailInfoVO.confirmedtime = response.result.confirmedtime
        txDetailInfoVO.contract = response.result.arguments
        txDetailInfoVO.rawtx = response.result.rawtx
        return txDetailInfoVO
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


//        var sendToAddressPO = SendToAddressPO()
//        sendToAddressPO.sendAddress = bcWiccSendTransactionLog.sendAddress
//        sendToAddressPO.recvAddress = bcWiccSendTransactionLog.recvAddress
//        sendToAddressPO.amount = coinSendTransactionPO.amount
        var sendToAddressWithFeePO = SendToAddressWithFeePO()
        sendToAddressWithFeePO.sender = bcWiccSendTransactionLog.sendAddress
        sendToAddressWithFeePO.recviver = bcWiccSendTransactionLog.recvAddress
        sendToAddressWithFeePO.amount = coinSendTransactionPO.amount
        sendToAddressWithFeePO.fee =  coinSendTransactionPO.fee?: BigDecimal(TransactionConstantDict.SENDTOADDRESS_DEFAULT_FEE.value)
        var reponse= wiccMethodClient.getClient().sendToAddressWithFee(sendToAddressWithFeePO)
        if(reponse.error != null) {
            bcWiccSendTransactionLog.remark = "[${reponse.error.code}],${reponse.error.message} "
            bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
            throw BizException(com.waykichain.chain.commons.biz.dict.ErrorCode.SYS_INTERNAL_ERROR.code, bcWiccSendTransactionLog.remark)

        }
        bcWiccSendTransactionLog.txid = reponse.result.hash
        bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
        return bcWiccSendTransactionLog.txid
    }

    override fun genAddress(addressType:Int?): CoinAddressVO {
        var coinAddressVO = CoinAddressVO()
        var reponse = wiccMethodClient.getClient().newAddress
        if(reponse.error != null) {
            throw BizException(com.waykichain.chain.commons.biz.dict.ErrorCode.SYS_INTERNAL_ERROR.code,
                    reponse.error.code.toString() + reponse.error.message)
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
        var currentBcNumber = wiccMethodClient.getClient().info.result.tipblockheight

        val coin = coinService.getBySymbol(com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol)

        valueCacheRedisRepository.put(
                "SYS_WICC_CHAIN_CURRENT_HEIGHT", currentBcNumber.toString(),
                60*60,
                TimeUnit.SECONDS)

        if(coin!!.startNumber  > lastBlockId!!)
            lastBlockId = coin!!.startNumber

        var dbTailNumber =  currentBcNumber - coin.miniConfirmCount

        ((lastBlockId!!.plus(1))!!..dbTailNumber).forEach {
            addNewBlock(it)
        }
    }

    override fun addNewBlock(blockId:Int) {
        val response = wiccMethodClient.getClient().getBlock(blockId)

        var bcWiccBlock = BcWiccBlock()
        response.result.let {
            bcWiccBlock.number = it.height
            bcWiccBlock.hash = it.hash
            bcWiccBlock.confirmations = it.confirmations
            bcWiccBlock.size = it.size
            bcWiccBlock.height = it.height
            bcWiccBlock.version = it.version
            bcWiccBlock.merkleRoot = it.merkleroot
            bcWiccBlock.time = Date(it.time * 1000)
            bcWiccBlock.chainwork = it.chainwork
            bcWiccBlock.nonce = it.nonce
            bcWiccBlock.fuel = it.fuel
            bcWiccBlock.fuelRate = it.fuelrate
            bcWiccBlock.previousBlockHash = it.previousblockhash
            bcWiccBlock.nextBlockHash = it.nextblockhash
        }

        var txdetails = ArrayList<WiccGetTxDetailResult>()

        response.result.tx.forEach {
            val txdetail = wiccMethodClient.getClient().getTxDetail(it).result
            txdetails.add(txdetail)
            logger.info("txdetail:" + Json.encode(txdetail))
            ///<TODO 修改余额变化表
            if(txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.COMMON_TX.type ||
                    txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.CONTRACT_TX.type ||
                    txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.REG_ACCT_TX.type ||
                    txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.REG_APP_TX.type) {
                    var money = txdetail.money
                    if(money == null) {
                        money = 0L
                    }
                    ///< 减小起转账人的余额
                    bcWiccWalletAccountService.addBalance(
                            txdetail.addr,
                            com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol,
                            0L.minus(money),
                            0L.minus(txdetail.fees),
                            txdetail.txtype,
                            txdetail.hash,
                            txdetail.confirmedheight,
                            Date(txdetail.confirmedtime * 1000),
                            null)
                }

            if(txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.COMMON_TX.type ||
                    txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.CONTRACT_TX.type) {
                    bcWiccWalletAccountService.addBalance(
                            txdetail.desaddr,
                            com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol,
                            txdetail.money,
                            0,
                            txdetail.txtype,
                            txdetail.hash,
                            txdetail.confirmedheight,
                            WiccUtils.getGmt0(txdetail.confirmedtime),
                            null)
            }
            if(txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.REWARD_TX.type ){
                    //<旷工奖励
                    bcWiccWalletAccountService.addBalance(
                            txdetail.addr,
                            com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol,
                            txdetail.money,
                            0,
                            txdetail.txtype,
                            txdetail.hash,
                            txdetail.confirmedheight,
                            WiccUtils.getGmt0(txdetail.confirmedtime),
                            null)
                }

            if(txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.CONTRACT_TX.type) {
                    ///< 解析合约
            }
        }

        updateBlockInfo(bcWiccBlock, txdetails)
    }

    override fun submitOfflineTransaction(coinOfflineTransactionPO: CoinOfflineTransactionPO): String? {
        var bcWiccOfflineTransacationLog = BcWiccOfflineTransacationLog()
        bcWiccOfflineTransacationLog.requestUuid  = coinOfflineTransactionPO.requestUuid
        bcWiccOfflineTransacationLog.info = coinOfflineTransactionPO.offlineTransactionInfo
        bcWiccOfflineTransacationLog = bcWiccOfflineTransacationLogService.save(bcWiccOfflineTransacationLog)


        var reponse= wiccMethodClient.getClient().submitTx(coinOfflineTransactionPO.offlineTransactionInfo!!)
        if(reponse.error != null) {
            bcWiccOfflineTransacationLog.remark = "[${reponse.error.code}],${reponse.error.message} "
            bcWiccOfflineTransacationLogService.save(bcWiccOfflineTransacationLog)
            throw BizException(com.waykichain.chain.commons.biz.dict.ErrorCode.SYS_INTERNAL_ERROR.code, bcWiccOfflineTransacationLog.remark)

        }
        bcWiccOfflineTransacationLog.txid = reponse.result.hash
        bcWiccOfflineTransacationLogService.save(bcWiccOfflineTransacationLog)
        return bcWiccOfflineTransacationLog.txid
    }

    override fun getChainInfo(): WiccInfoJsonRpcResponse? {
        return wiccMethodClient.getClient().info
    }

    @Transactional
    open fun updateBlockInfo(bcWiccBlock: BcWiccBlock, txdetails:List<WiccGetTxDetailResult>) {
        ///<Save BcBtcBlock
        ///<Save Transactions
        var bcWiccTransactions = ArrayList<BcWiccTransaction>()
        txdetails.forEach{
            var  bcWiccTransaction = BcWiccTransaction()
            bcWiccTransaction.blockNumber = bcWiccBlock.number
            bcWiccTransaction.blockHash = it.blockhash
            bcWiccTransaction.txid = it.hash
            bcWiccTransaction.txType = it.txtype
            bcWiccTransaction.ver = it.ver
            bcWiccTransaction.regid = it.regid
            bcWiccTransaction.addr = it.addr
            bcWiccTransaction.descregid = it.desregid
            bcWiccTransaction.desaddr = it.desaddr
            bcWiccTransaction.money = it.money
            bcWiccTransaction.fees = it.fees
            bcWiccTransaction.height = it.height
            bcWiccTransaction.contract = it.arguments
            if(bcWiccTransaction.contract == null)
                bcWiccTransaction.contract = it.arguments
            bcWiccTransaction.confirmHeight = it.confirmedheight
            bcWiccTransaction.confirmedTime = it.confirmedtime
            bcWiccTransaction.rawtx = it.rawtx
            bcWiccTransaction.pubkey = it.pubkey
            bcWiccTransaction.minerPubkey = it.minerpubkey
            bcWiccTransaction.script = it.script
            if (it.listOutput != null)
                bcWiccTransaction.listOutput = it.listOutput.toString()
            if (it.operVoteFundList != null) {
                bcWiccTransaction.operVoteFundList  = JSON.toJSONString(it.operVoteFundList)
            }

            bcWiccTransactions.add(bcWiccTransaction)
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
        val response = wiccMethodClient.getClient().activeAddress(queryAccountInfoPO.address)
        if(response.error != null) {
            throw BizException(com.waykichain.chain.commons.biz.dict.ErrorCode.SYS_INTERNAL_ERROR.code,
                    response.error.code.toString() + response.error.message)
        }

        return response.result.hash
    }


    override fun createContract(createContractPO: CreateContractPO): String? {

        val response = wiccMethodClient.getClient().createContract(createContractPO)
        if (response.error != null)  {
            throw BizException(ErrorCode.SYS_INTERNAL_ERROR.code, ErrorCode.SYS_INTERNAL_ERROR.msg)
        }

        return response.result.hash
    }

    override fun getBlockCount(): Long? {
        val response = wiccMethodClient.getClient().getBlockCount()
        if (response.error != null) {
            throw BizException(response.error.code, response.error.message);
        }

        return response.result;
    }

    override fun getBlockByHeight(height: Int): BcWiccBlock{
        val response = wiccMethodClient.getClient().getBlock(height)
        var bcWiccBlock = BcWiccBlock()
        response.result.let {
            bcWiccBlock.number = it.height
            bcWiccBlock.hash = it.hash
            bcWiccBlock.confirmations = it.confirmations
            bcWiccBlock.size = it.size
            bcWiccBlock.height = it.height
            bcWiccBlock.version = it.version
            bcWiccBlock.merkleRoot = it.merkleroot
            bcWiccBlock.time = Date(it.time * 1000)
            bcWiccBlock.chainwork = it.chainwork
            bcWiccBlock.nonce = it.nonce
            bcWiccBlock.fuel = it.fuel
            bcWiccBlock.fuelRate = it.fuelrate
            bcWiccBlock.previousBlockHash = it.previousblockhash
            bcWiccBlock.nextBlockHash = it.nextblockhash
        }
        return bcWiccBlock
    }

    override fun getBlockHash(blockId:Int): String? {
        val response = wiccMethodClient.getClient().getBlockHash(blockId)
        if (response.error !=null) {
            throw BizException(response.error.code, response.error.message)
        }

        return response.result.hash;
    }

    override fun getTotalCoin(): BigDecimal? {
        val response = wiccMethodClient.getClient().getTotalCoin()
        if (response.error != null) {
            throw BizException(response.error.code, response.error.message)
        }

        return response.result?.totalCoin
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
                if(rollBackBlock.hash != response.result.hash) {

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
                        it.hash = response.result.hash
                        it.confirmations = response.result.confirmations
                        it.size = response.result.size
                        it.height = response.result.height
                        it.version = response.result.version
                        it.merkleRoot = response.result.merkleroot
                        it.time = Date(response.result.time * 1000)
                        it.chainwork = response.result.chainwork
                        it.nonce = response.result.nonce
                        it.fuel = response.result.fuel
                        it.fuelRate = response.result.fuelrate
                        it.previousBlockHash = response.result.previousblockhash
                        it.nextBlockHash = response.result.nextblockhash
                        bcWiccBlockService.save(it)
                    }

                    /** -修改相邻区块信息*/
                    var previousBlock = bcWiccBlockService.getByHeight(rollBackBlock.height - 1)
                    if (previousBlock != null) {
                        previousBlock.nextBlockHash = response.result.hash
                        bcWiccBlockService.save(previousBlock)
                    }
                    var nextBlock = bcWiccBlockService.getByHeight(rollBackBlock.height + 1)
                    if (nextBlock != null) {
                        nextBlock.previousBlockHash = response.result.hash
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
                if (block.hash != response.result.hash) {

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
            if(tran.txType == com.waykichain.chain.commons.biz.dict.WiccTransacationType.COMMON_TX.type ||
                    tran.txType == com.waykichain.chain.commons.biz.dict.WiccTransacationType.CONTRACT_TX.type ||
                    tran.txType == com.waykichain.chain.commons.biz.dict.WiccTransacationType.REG_ACCT_TX.type ||
                    tran.txType == com.waykichain.chain.commons.biz.dict.WiccTransacationType.REG_APP_TX.type) {

                //增加起转账人的余额
                bcWiccWalletAccountService.addBalance(
                        tran.addr,
                        com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol,
                        tran.money?:0L,
                        tran.fees,
                        tran.txType,
                        tran.txid,
                        tran.confirmHeight,
                        Date(tran.confirmedTime * 1000),
                        "rollback")
            }

            if(tran.txType == com.waykichain.chain.commons.biz.dict.WiccTransacationType.COMMON_TX.type ||
                    tran.txType == com.waykichain.chain.commons.biz.dict.WiccTransacationType.CONTRACT_TX.type) {
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
            if(tran.txType == com.waykichain.chain.commons.biz.dict.WiccTransacationType.REWARD_TX.type ){
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

            if(tran.txType == com.waykichain.chain.commons.biz.dict.WiccTransacationType.CONTRACT_TX.type) {
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

        val txdetail = wiccMethodClient.getClient().getTxDetail(hash).result

        logger.info("[scan chain rollback] Synchronize data from chain, transaction=${Json.encode(txdetail)}")
        txdetail?.let {

            var  bcWiccTransaction = BcWiccTransaction()
            bcWiccTransaction.blockNumber = height
            bcWiccTransaction.blockHash = it.blockhash
            bcWiccTransaction.txid = it.hash
            bcWiccTransaction.txType = it.txtype
            bcWiccTransaction.ver = it.ver
            bcWiccTransaction.regid = it.regid
            bcWiccTransaction.addr = it.addr
            bcWiccTransaction.descregid = it.desregid
            bcWiccTransaction.desaddr = it.desaddr
            bcWiccTransaction.money = it.money
            bcWiccTransaction.fees = it.fees
            bcWiccTransaction.height = it.height
            bcWiccTransaction.contract = it.arguments
            if(bcWiccTransaction.contract == null)
                bcWiccTransaction.contract = it.arguments
            bcWiccTransaction.confirmHeight = it.confirmedheight
            bcWiccTransaction.confirmedTime = it.confirmedtime
            bcWiccTransaction.rawtx = it.rawtx
            bcWiccTransaction.pubkey = it.pubkey
            bcWiccTransaction.minerPubkey = it.minerpubkey
            bcWiccTransaction.script = it.script
            if (it.listOutput != null)
                bcWiccTransaction.listOutput = it.listOutput.toString()
            if (it.operVoteFundList != null) {
                bcWiccTransaction.operVoteFundList  = JSON.toJSONString(it.operVoteFundList)
            }
            bcWiccTransactions.add(bcWiccTransaction)

        }

        if(txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.COMMON_TX.type ||
                txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.CONTRACT_TX.type ||
                txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.REG_ACCT_TX.type ||
                txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.REG_APP_TX.type) {
            var money = txdetail.money
            if(money == null) {
                money = 0L
            }
            ///< 减小起转账人的余额
            bcWiccWalletAccountService.addBalance(
                    txdetail.addr,
                    com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol,
                    0L.minus(money),
                    0L.minus(txdetail.fees),
                    txdetail.txtype,
                    txdetail.hash,
                    txdetail.confirmedheight,
                    Date(txdetail.confirmedtime * 1000),
                    "rollback,sync again")
        }

        if(txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.COMMON_TX.type ||
                txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.CONTRACT_TX.type) {
            bcWiccWalletAccountService.addBalance(
                    txdetail.desaddr,
                    com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol,
                    txdetail.money,
                    0,
                    txdetail.txtype,
                    txdetail.hash,
                    txdetail.confirmedheight,
                    WiccUtils.getGmt0(txdetail.confirmedtime),
                    "rollback,sync again")
        }
        if(txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.REWARD_TX.type ){
            //<旷工奖励
            bcWiccWalletAccountService.addBalance(
                    txdetail.addr,
                    com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol,
                    txdetail.money,
                    0,
                    txdetail.txtype,
                    txdetail.hash,
                    txdetail.confirmedheight,
                    WiccUtils.getGmt0(txdetail.confirmedtime),
                    "rollback,sync again")
        }

        if(txdetail.txtype == com.waykichain.chain.commons.biz.dict.WiccTransacationType.CONTRACT_TX.type) {
            ///< 解析合约
        }
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

    @Autowired
    private val txManager: PlatformTransactionManager? = null

}