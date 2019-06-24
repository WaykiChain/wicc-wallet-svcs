package com.waykichain.chain.commons.biz.xservice.impl

import com.waykichain.chain.biz.domain.BcWiccOfflineTransacationLog
import com.waykichain.chain.biz.domain.BcWiccSendTransactionLog
import com.waykichain.chain.commons.biz.dict.CoinType
import com.waykichain.chain.commons.biz.dict.ErrorCode
import com.waykichain.chain.commons.biz.dict.TransactionConstantDict
import com.waykichain.chain.commons.biz.dict.TransactionDirectionDict
import com.waykichain.chain.commons.biz.env.Environment
import com.waykichain.chain.commons.biz.service.BcWiccBlockService
import com.waykichain.chain.commons.biz.service.BcWiccOfflineTransacationLogService
import com.waykichain.chain.commons.biz.service.BcWiccSendTransactionLogService
import com.waykichain.chain.commons.biz.service.BcWiccTransactionService
import com.waykichain.chain.commons.biz.xservice.BcWiccSendTransactionLogXService
import com.waykichain.chain.commons.biz.xservice.TransactionXService
import com.waykichain.chain.commons.biz.xservice.WiccMethodClient
import com.waykichain.chain.po.v2.*
import com.waykichain.chain.vo.v2.*
import com.waykichain.coin.wicc.po.DecodeRawTxPO
import com.waykichain.coin.wicc.po.GenSendToAddressTxRawPO
import com.waykichain.coin.wicc.po.SendToAddressPO
import com.waykichain.coin.wicc.po.SendToAddressWithFeePO
import com.waykichain.commons.base.BizResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.*

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/4 19:54
 *
 * @Description:    交易相关
 *
 */
@Service
open class TransactionXServiceImpl: TransactionXService {

    /**
     * 根据交易地址获取交易记录
     *
     */
    override fun getTransactions(po: TransactionQueryPO): BizResponse<WiccTransactionsVO> {

        var transactionsVO = WiccTransactionsVO()
        //当前区块高度
        transactionsVO.currentheight = bcWiccBlockService.getLastBlockId()
        var cond = com.waykichain.chain.commons.biz.cond.QueryTransactionCond()
        cond.address = po.address
        cond.startNumber = po.startheight
        cond.tranDirection = po.trandirection?:null
        cond.pageSize = po.pagesize
        cond.currentPage = po.currentpage
        cond.txtype = po.txtype
        val result = bcWiccTransactionService.getByAddressV2(cond)

        transactionsVO.currentpage = po.currentpage
        transactionsVO.totalpages = result.second
        transactionsVO.totalcount = result.third

        result.first?.let {
            for (bean in it) {
                val transaction = WiccTransactionVO()
                transaction.money =  if (null != bean.money)  BigDecimal(bean.money) else BigDecimal.ZERO
                transaction.addr = bean.addr
                transaction.desaddr = bean.desaddr
                transaction.symbol = CoinType.WICC.msg
                transaction.hash = bean.txid
                transaction.blockhash = bean.blockHash
                transaction.confirmedtime = Date(bean.confirmedTime)
                transaction.confirmedheight = bean.confirmHeight
                if (null != bean.fees) transaction.fees = BigDecimal(bean.fees)
                transaction.arguments = bean.contract
                transaction.regid = bean.regid
                transaction.desregid = bean.descregid
                transaction.height = bean.height
                transaction.rawtx = bean.rawtx
                if (null != bean.txType) transaction.txtype = bean.txType
                transaction.trandirection =  if (po.address.equals( bean.addr)) TransactionDirectionDict.TRAN_DIRECTION_IN.numValue
                              else if (po.address.equals( bean.desaddr)) TransactionDirectionDict.TRAN_DIRECTION_OUT.numValue
                              else TransactionDirectionDict.TRAN_DIRECTION_OTHER.numValue
                transactionsVO.transactions!!.add(transaction)
            }

        }

        return BizResponse(transactionsVO)
    }

    /**
     * 根据交易哈希获取交易详情(查本地表)
     */
   override fun getTranDetailByTX(po: WiccTransactionDetailPO): BizResponse<WiccTransactionVO> {

        val bean = bcWiccTransactionService.getByTxid(po.hash!!)
        val transaction = WiccTransactionVO()
        transaction.money =  if (null != bean.money)  BigDecimal(bean.money) else BigDecimal.ZERO
        transaction.addr = bean.addr
        transaction.desaddr = bean.desaddr
        transaction.symbol = CoinType.WICC.msg
        transaction.hash = bean.txid
        transaction.blockhash = bean.blockHash
        transaction.confirmedtime = Date(bean.confirmedTime)
        transaction.confirmedheight = bean.confirmHeight
        if (null != bean.fees) transaction.fees = BigDecimal(bean.fees)
        transaction.arguments = bean.contract
        transaction.regid = bean.regid
        transaction.desregid = bean.descregid
        transaction.height = bean.height
        transaction.rawtx = bean.rawtx
        if (null != bean.txType) transaction.txtype = bean.txType
        return BizResponse(transaction)
    }


    /**
     * 根据交易哈希获取交易详情(调用RPC)
     */
    override fun getTranDetailFromRPC(hash: String): BizResponse<WiccTransactionVO> {

        val response= wiccMethodClient.getClient().getTxDetail(hash)
        var bizResponse = BizResponse<WiccTransactionVO>()
        if(response.result != null) {
            val transaction = WiccTransactionVO()
            transaction.money =  if (null != response.result.money) BigDecimal(response.result.money) else BigDecimal.ZERO
            transaction.addr = response.result.addr
            transaction.desaddr = response.result.desaddr
            transaction.symbol = CoinType.WICC.msg
            transaction.hash = response.result.hash
            transaction.blockhash = response.result.blockhash
            if (response.result.confirmedtime != null) transaction.confirmedtime = Date(response.result.confirmedtime)
            transaction.confirmedheight = response.result.confirmedheight
            if (null != response.result.fees) transaction.fees = BigDecimal(response.result.fees)
            transaction.arguments = response.result.arguments
            transaction.regid = response.result.regid
            transaction.desregid = response.result.desregid
            transaction.height = response.result.height
            transaction.rawtx = response.result.rawtx
            transaction.txtype = response.result.txtype

            bizResponse.data = transaction
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
     * 从源地址账户转账到目的地址账户，手续费默认为10000sawi [RPC-sendtoaddress]
     */
    @Transactional
    override fun sendtoaddress(po: SendtoaddressPO): BizResponse<SendtoaddressVO> {

//        val oldLog = bcWiccSendTransactionLogService.getByRequestUuid(po.uuid!!)
//        if (oldLog != null ) {
//            return BizResponse(ErrorCode.UUID_IS_USED.code, ErrorCode.UUID_IS_USED.msg)
//        }

        var bcWiccSendTransactionLog = BcWiccSendTransactionLog()
        bcWiccSendTransactionLog.amount = po.amount!!.toLong()
        bcWiccSendTransactionLog.transFee = TransactionConstantDict.SENDTOADDRESS_DEFAULT_FEE.numValue
        bcWiccSendTransactionLog.transAmount =bcWiccSendTransactionLog.amount - bcWiccSendTransactionLog.transFee
        bcWiccSendTransactionLog.sendAddress = po.sender
        bcWiccSendTransactionLog.recvAddress = po.recviver
        bcWiccSendTransactionLog.requestUuid = "auto-"+UUID.randomUUID().toString().replace("-","")
        bcWiccSendTransactionLog = bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
        var sendToAddressPO = SendToAddressPO()
        sendToAddressPO.sendAddress = po.sender
        sendToAddressPO.recvAddress = po.recviver
        sendToAddressPO.amount = po.amount

        var response= wiccMethodClient.getClient().sendToAddress(sendToAddressPO)
        var bizResponse = BizResponse<SendtoaddressVO>()
        if (response.result != null) {
            bcWiccSendTransactionLog.txid = response.result.hash
            var sendtoaddressVO = SendtoaddressVO()
            sendtoaddressVO.hash = response.result.hash
            bizResponse.data = sendtoaddressVO
        } else {
            if (response.error != null) {
                bcWiccSendTransactionLog.remark = "[${response.error.code}],${response.error.message} "
                bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)

                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
        return bizResponse
    }

    /**
     * 从源地址账户转账到目的地址账户,手动设置手续费 [RPC-sendtoaddresswithfee]
     */
    @Transactional
    override fun sendtoaddressWithFee(po: SendtoAddressWithFeePO): BizResponse<SendtoaddressVO> {

//        val oldLog = bcWiccSendTransactionLogService.getByRequestUuid(po.uuid!!)
//        if (oldLog != null ) {
//            return BizResponse(ErrorCode.UUID_IS_USED.code, ErrorCode.UUID_IS_USED.msg)
//        }

        var bcWiccSendTransactionLog = BcWiccSendTransactionLog()
        bcWiccSendTransactionLog.amount = po.amount!!.toLong()
        bcWiccSendTransactionLog.transFee = po.fee!!.toLong()
        bcWiccSendTransactionLog.transAmount =bcWiccSendTransactionLog.amount - bcWiccSendTransactionLog.transFee
        bcWiccSendTransactionLog.sendAddress = po.sender
        bcWiccSendTransactionLog.recvAddress = po.recviver
        bcWiccSendTransactionLog.requestUuid = "auto-"+UUID.randomUUID().toString().replace("-","")
        bcWiccSendTransactionLog = bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
        var sendToAddressWithFeePO = SendToAddressWithFeePO()
        sendToAddressWithFeePO.sender = po.sender
        sendToAddressWithFeePO.recviver = po.recviver
        sendToAddressWithFeePO.amount = po.amount
        sendToAddressWithFeePO.fee = po.fee

        var response= wiccMethodClient.getClient().sendToAddressWithFee(sendToAddressWithFeePO)
        var bizResponse = BizResponse<SendtoaddressVO>()
        if (response.result != null) {
            bcWiccSendTransactionLog.txid = response.result.hash
            var sendtoaddressVO = SendtoaddressVO()
            sendtoaddressVO.hash = response.result.hash
            bizResponse.data = sendtoaddressVO
        } else {
            if (response.error != null) {
                bcWiccSendTransactionLog.remark = "[${response.error.code}],${response.error.message} "
                bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)

                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                logger.error("sendtoaddressWithFee() error, response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
        return bizResponse
    }

    /**
     * 创建交易签名字段,手动设置手续费,可用 submittx 方法进行提交交易
     * [RPC-gensendtoaddresstxraw]
     */
    override fun genSendtoAddresstxraw(po: GenSendToAddressTxrawPO): BizResponse<GenSendToAddressTxrawVO> {


        val heightResponse = wiccMethodClient.getClient().getBlockCount()
        if (heightResponse == null || heightResponse.result == null) {return BizResponse(ErrorCode.GET_BLOCK_HEIGHT_FAIL.code,ErrorCode.GET_BLOCK_HEIGHT_FAIL.msg) }

        var genSendToAddressTxrawPO = GenSendToAddressTxRawPO()
        genSendToAddressTxrawPO.fee = po.fee
        genSendToAddressTxrawPO.amount = po.amount
        genSendToAddressTxrawPO.sender = po.sender
        genSendToAddressTxrawPO.recviver = po.recviver
        genSendToAddressTxrawPO.height = heightResponse.result
        val response= wiccMethodClient.getClient().genSendtoAddresstxraw(genSendToAddressTxrawPO)
        var bizResponse = BizResponse<GenSendToAddressTxrawVO>()
        if(response.result != null) {
            val vo = GenSendToAddressTxrawVO()
            vo.rawtx = response.result.rawtx
            bizResponse.data = vo
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
     * 获取当前节点交易列表：包含已确认和未确认的交易
     *
     * [RPC-listtx]
     */
     override fun listTx(): BizResponse<ListTxVO> {

        val response= wiccMethodClient.getClient().listTx()
        var bizResponse = BizResponse<ListTxVO>()
        if(response.result != null) {
            val vo = ListTxVO()
            vo.confirmtx = response.result.ConfirmTx
            vo.unconfirmtx = response.result.unConfirmTx
            bizResponse.data = vo
        } else {
            if (response.error != null) {
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                logger.error("listTx() error, response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }

        return bizResponse
    }

    /**
     * 根据签名字段解析原始交易单
     *
     * [RPC-decoderawtx]
     */
    override fun decodeRawtx(po: DecodeRawtxPO): BizResponse<DecodeRawtxVO> {

        var decodeRawTxPO = DecodeRawTxPO()
        decodeRawTxPO.hexstring = po.rawtx
        val response= wiccMethodClient.getClient().decodeRawtx(decodeRawTxPO)
        var bizResponse = BizResponse<DecodeRawtxVO>()

        if(response.result != null) {
            val vo = DecodeRawtxVO()
            BeanUtils.copyProperties(response.result, vo)
            vo.arguments = response.result.arguments
            bizResponse.data = vo
        } else {
            if (response.error != null) {
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                logger.error("listTx() error, response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }

        return bizResponse
    }

    /**
     * 更新UUID
     */
    override fun updateUUID(po: UpdateUuidPO): BizResponse<Any> {
        bcWiccSendTransactionLogXService.updateBcWiccSendTransactionLogRequestUuid(po.requestUUID!!)
        return BizResponse(null)
    }


    /**
     * 发起离线交易[RPC-submittx]
     */
    override fun submitOfflineTransaction(po: OfflineTransactionPO): BizResponse<WiccTransactionVO> {
        var bcWiccOfflineTransacationLog = BcWiccOfflineTransacationLog()
        bcWiccOfflineTransacationLog.requestUuid  = "auto-"+UUID.randomUUID().toString().replace("-","")
        bcWiccOfflineTransacationLog.info = po.rawtx
        bcWiccOfflineTransacationLog = bcWiccOfflineTransacationLogService.save(bcWiccOfflineTransacationLog)
        var response= wiccMethodClient.getClient().submitTx(po.rawtx)
        var bizResponse = BizResponse<WiccTransactionVO>()

        if (response.result != null) {

            bcWiccOfflineTransacationLog.txid = response.result.hash
            bcWiccOfflineTransacationLogService.save(bcWiccOfflineTransacationLog)
            val result = getTranDetailFromRPC(response.result.hash)
            bizResponse = result
        } else {
            if(response.error != null) {
                bcWiccOfflineTransacationLog.remark = "[${response.error.code}],${response.error.message} "
                bcWiccOfflineTransacationLogService.save(bcWiccOfflineTransacationLog)
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
     * 获取测试金额
     * TODO 是否要对每个账户做请求次数限制
     */
    override fun depositTestMoney(recviver:String, money: String): BizResponse<SendtoaddressVO> {

        var  sendToAddressPO = SendToAddressPO()
        sendToAddressPO.amount = BigDecimal(money)
        sendToAddressPO.sendAddress = Environment.TEST_MONEY_SENDER_ADDRESS
        sendToAddressPO.recvAddress = recviver
        var response = wiccMethodClient.getClient().sendToAddress(sendToAddressPO)
        var bizResponse = BizResponse<SendtoaddressVO>()

        if(response.result != null) {
            var sendtoaddressVO = SendtoaddressVO()
            sendtoaddressVO.hash = response.result.hash
            bizResponse.data = sendtoaddressVO
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
    lateinit var bcWiccTransactionService: BcWiccTransactionService

    @Autowired
    lateinit var bcWiccSendTransactionLogService: BcWiccSendTransactionLogService

    @Autowired
    lateinit var bcWiccSendTransactionLogXService: BcWiccSendTransactionLogXService

    @Autowired
    lateinit var bcWiccOfflineTransacationLogService: BcWiccOfflineTransacationLogService
}