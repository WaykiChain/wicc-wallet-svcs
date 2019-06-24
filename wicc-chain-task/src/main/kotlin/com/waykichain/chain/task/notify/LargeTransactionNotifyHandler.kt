package com.waykichain.chain.task.notify

import com.waykichain.chain.biz.domain.BcWiccAlertLog
import com.waykichain.chain.biz.domain.BcWiccTransaction
import com.waykichain.chain.biz.domain.QBcWiccTransaction
import com.waykichain.chain.biz.domain.QSysChainMsgNotifySetting
import com.waykichain.chain.commons.biz.env.Environment
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccAlertLogRepository
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccMonitorAddressRepository
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccTransactionRepository
import com.waykichain.chain.commons.biz.repository.mysql.SysChainMsgNotifySettingRepository
import com.waykichain.chain.commons.biz.service.BcWiccBlockService
import com.waykichain.chain.commons.biz.utils.WiccUtils
import com.waykichain.chain.commons.biz.xservice.DingTalkService
import com.xxl.job.core.biz.model.ReturnT
import com.xxl.job.core.handler.IJobHandler
import com.xxl.job.core.handler.annotation.JobHandler
import com.xxl.job.core.log.XxlJobLogger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal.ROUND_UP

/**
 * Created by Joss
 * @date 2018/4/8
 */
@Service
@JobHandler(value = "largeTransactionNotifyHandler")
class LargeTransactionNotifyHandler:IJobHandler() {

    /**
     * @param params
     */
    override fun execute(params: String?): ReturnT<String> {

        XxlJobLogger.log("大额交易扫描开始")

        scanTransaction()
        XxlJobLogger.log("大额交易扫描结束")

        return ReturnT.SUCCESS
    }


    fun scanTransaction() {
        var sendUrl:String? = null
        var chainMsgNotifySettingList = sysChainMsgNotifySettingRepository.findAll(QSysChainMsgNotifySetting.sysChainMsgNotifySetting.msgId.eq(10.toString()))
        if( chainMsgNotifySettingList != null && chainMsgNotifySettingList.count() !=  0) {
            sendUrl = chainMsgNotifySettingList.first()!!.msgUrl!!
        }
        val monitorAddresses = bcWiccMonitorAddressRepository.findAll()
        var currentNumber = bcWiccBlockService.getLastBlockId()
        monitorAddresses.forEach {
            /**
             * 发送交易
             */
            val transactions = bcWiccTransactionRepository.findAll(QBcWiccTransaction.bcWiccTransaction.addr.eq(it.address).
                    and(QBcWiccTransaction.bcWiccTransaction.blockNumber.between(it.heigh, currentNumber)).
                    and(QBcWiccTransaction.bcWiccTransaction.money.gt(Environment.MINI_TRANSER_AMOUNT)))

            if(transactions !=null && transactions.count() > 0) {
                val message = "发生大额转出, 钱包地址: ${it.address}" +
                        convertSendTransacationToNotifyMessage(transactions.toList())
                XxlJobLogger.log("发送大额转出交易: $message")
                dingTalkService.sendTextMessage(sendUrl!!, message)
                var bcWiccAlertLog = BcWiccAlertLog()
                bcWiccAlertLog.address = it.address
                bcWiccAlertLog.notifyLinkUrl=  sendUrl
                bcWiccAlertLog.alertInfo = message
                bcWiccAlertLogRepository.save(bcWiccAlertLog)
            }

            val totransactions = bcWiccTransactionRepository.findAll(QBcWiccTransaction.bcWiccTransaction.desaddr.eq(it.address).
                    and(QBcWiccTransaction.bcWiccTransaction.blockNumber.between(it.heigh, currentNumber)).
                    and(QBcWiccTransaction.bcWiccTransaction.money.gt(Environment.MINI_TRANSER_AMOUNT)))

            if(totransactions !=null && totransactions.count() > 0) {
                val message = "发生大额转入, 钱包地址: ${it.address}" + convertRecvTransacationToNotifyMessage(totransactions.toList())
                XxlJobLogger.log("发送大额转入交易: $message")
                dingTalkService.sendTextMessage(sendUrl!!, message)
                var bcWiccAlertLog = BcWiccAlertLog()
                bcWiccAlertLog.address = it.address
                bcWiccAlertLog.alertInfo = message
                bcWiccAlertLog.notifyLinkUrl=  sendUrl
                bcWiccAlertLogRepository.save(bcWiccAlertLog)
            }
            it.heigh = currentNumber
            bcWiccMonitorAddressRepository.save(it)
        }
    }

    fun convertSendTransacationToNotifyMessage(transactions: List<BcWiccTransaction>): String {
        var message =""
        transactions.forEach {
            var amount = WiccUtils.convert(it.money).setScale(3, ROUND_UP)
            message +=  "转出金额: $amount 交易hash: ${it.txid} \n"
        }
        return message
    }

    fun convertRecvTransacationToNotifyMessage(transactions: List<BcWiccTransaction>): String {
        var message = "发生大额转入: \n"
        transactions.forEach {
            var amount = WiccUtils.convert(it.money).setScale(3,ROUND_UP)
            message += "转入金额: $amount  交易hash: ${it.txid} \n"
        }
        return message
    }

    @Autowired lateinit var bcWiccMonitorAddressRepository: BcWiccMonitorAddressRepository
    @Autowired lateinit var bcWiccTransactionRepository: BcWiccTransactionRepository
    @Autowired lateinit var bcWiccBlockService: BcWiccBlockService
    @Autowired lateinit var sysChainMsgNotifySettingRepository: SysChainMsgNotifySettingRepository
    @Autowired lateinit var dingTalkService:DingTalkService
    @Autowired lateinit var bcWiccAlertLogRepository: BcWiccAlertLogRepository
    private val logger = LoggerFactory.getLogger(javaClass)
}