package com.waykichain.chain.commons.biz.xservice.impl

import com.alibaba.fastjson.JSON
import com.waykichain.bet.commons.biz.exception.BizException
import com.waykichain.chain.biz.domain.*
import com.waykichain.chain.commons.biz.dict.ErrorCode
import com.waykichain.chain.commons.biz.env.coin.Environment
import com.waykichain.chain.commons.biz.service.*
import com.waykichain.chain.commons.biz.utils.WiccUtils
import com.waykichain.chain.commons.biz.xservice.CoinHandler
import com.waykichain.chain.commons.biz.xservice.WiccMethodClient
import com.waykichain.chain.po.*
import com.waykichain.chain.vo.*
import com.waykichain.coin.wicc.po.CreateContractTxPO
import com.waykichain.coin.wicc.po.SendToAddressPO
import com.waykichain.coin.wicc.vo.WiccGetTxDetailResult
import com.waykichain.coin.wicc.vo.WiccInfoJsonRpcResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.*

/**
 * Created by Joss on 05/16/18.
 */

@Service("wiccXservice")
open class WiccXserviceImpl : CoinHandler() {


    init {
        regHandler(com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol, this)
    }

    override fun getAccountInfo(address: String): AccountInfoVO? {
        var accountInfoResponse = wiccMethodClient.getClient().getAccountInfo(address)
        var accountInfoVO = AccountInfoVO()
        accountInfoVO.address = accountInfoResponse.result.Address
        accountInfoVO.balance = accountInfoResponse.result.balance
        accountInfoVO.keyID = accountInfoResponse.result.keyID
        accountInfoVO.minerPKey = accountInfoResponse.result.minerPKey
        accountInfoVO.regID = accountInfoResponse.result.regID
        accountInfoVO.postion = accountInfoResponse.result.postion
        accountInfoVO.updateHeight = accountInfoResponse.result.updateHeight
        accountInfoVO.votes = accountInfoResponse.result.votes
        accountInfoVO.publicKey = accountInfoResponse.result.publicKey
        return accountInfoVO

    }

    override fun sendContractTx(contractTransactionPO : ContractTransactionPO): String? {
        val bcWiccContractInfo = bcWiccContractInfoService.getByAddress(contractTransactionPO.contractAddress)
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
        txidDetailVO.contract = response.result.contract
        return txidDetailVO

    }

    @Transactional
    override fun sendTransaction(coinSendTransactionPO: CoinSendTransactionPO): String? {
        var bcWiccSendTransactionLog = BcWiccSendTransactionLog()
        bcWiccSendTransactionLog.amount = coinSendTransactionPO.amount!!.toLong()
        bcWiccSendTransactionLog.transFee = coinSendTransactionPO.fee!!.toLong()
        bcWiccSendTransactionLog.transAmount =bcWiccSendTransactionLog.amount - bcWiccSendTransactionLog.transFee

        bcWiccSendTransactionLog.recvAddress = coinSendTransactionPO.recvAddress
        bcWiccSendTransactionLog.requestUuid = coinSendTransactionPO.requestUUID
        bcWiccSendTransactionLog = bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)


        var sendToAddressPO = SendToAddressPO()
        sendToAddressPO.sendAddress
        sendToAddressPO.recvAddress = bcWiccSendTransactionLog.recvAddress
        sendToAddressPO.amount = coinSendTransactionPO.amount
        var reponse= wiccMethodClient.getClient().sendToAddress(sendToAddressPO)
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
        var transactionsVO = TransactionsVO()

        transactionsVO.currentNumber = bcWiccBlockService.getLastBlockId()
        var cond = com.waykichain.chain.commons.biz.cond.QueryTransactionCond()
        cond.recvAddress = queryTransactionPO.recvAddress
        cond.startNumber = queryTransactionPO.startNumber
//        val bcEthTransactions = bcBtcTransactionOutDetailService.getByCond(cond)
//        transactionsVO.transactions = BtcUtils.convert(bcEthTransactions)
        return transactionsVO
    }

    override fun scanBlock() {

        logger.info( "url info:${Environment.WICC_HOST_IP}, ${Environment.WICC_HOST_PORT}")

        /**获取数据库当前最新block*/
        var lastBlockId= bcWiccBlockService.getLastBlockId()

        /**获取最新链上最新block*/
        var currentBcNumber = wiccMethodClient.getClient().info.result.blocks

        val coin = coinService.getBySymbol(com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol)

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
                            txdetail.confirmHeight,
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
                            txdetail.confirmHeight,
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
                            txdetail.confirmHeight,
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
            bcWiccTransaction.contract = it.contract
            bcWiccTransaction.confirmHeight = it.confirmHeight
            bcWiccTransaction.confirmedTime = it.confirmedtime
            bcWiccTransaction.rawtx = it.rawtx
            bcWiccTransaction.pubkey = it.pubkey
            bcWiccTransaction.minerPubkey = it.minerPubkey
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

}