package com.waykichain.chain.task.notify

import com.waykichain.chain.biz.domain.BcWiccAlertLog
import com.waykichain.chain.biz.domain.BcWiccTransaction
import com.waykichain.chain.biz.domain.QBcWiccMonitorAddress
import com.waykichain.chain.biz.domain.QSysChainMsgNotifySetting
import com.waykichain.chain.commons.biz.dict.CoinType
import com.waykichain.chain.commons.biz.dict.NotifyMessageIdDict
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccMonitorAddressRepository
import com.waykichain.chain.commons.biz.repository.mysql.SysChainMsgNotifySettingRepository
import com.waykichain.chain.commons.biz.service.BcWiccBlockService
import com.waykichain.chain.commons.biz.utils.WiccUtils
import com.waykichain.chain.commons.biz.xservice.CoinHandler
import com.waykichain.chain.commons.biz.xservice.DingTalkService
import com.xxl.job.core.biz.model.ReturnT
import com.xxl.job.core.handler.IJobHandler
import com.xxl.job.core.handler.annotation.JobHandler
import com.xxl.job.core.log.XxlJobLogger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal.ROUND_UP
import kotlin.math.absoluteValue

/**
 * Created by Joss
 * @date 2018/4/8
 */
@Service
@JobHandler(value = "largeBalanceNotifyHandler")
class LargeBalanceNotifyHandler : IJobHandler() {


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
        var currentNumber = bcWiccBlockService.getLastBlockId()


        var chainMsgNotifySettingList = sysChainMsgNotifySettingRepository.findAll(
                QSysChainMsgNotifySetting.sysChainMsgNotifySetting.msgId.eq(NotifyMessageIdDict.MONEY_CHANGE_NOTIFY.code))
        if( chainMsgNotifySettingList != null && chainMsgNotifySettingList.count() !=  0) {
            sendUrl = chainMsgNotifySettingList.first()!!.msgUrl!!
        }



        val monitorAddresses = bcWiccMonitorAddressRepository.findAll(QBcWiccMonitorAddress.bcWiccMonitorAddress.active.eq(1))
        monitorAddresses.forEach {
            val accountInfo = CoinHandler.getHandler(CoinType.WICC.symbol)!!.getAccountInfo(it.address)

            var changeAmount= accountInfo!!.balance!!.toLong() - it.currentBalance!!
            var percent = 100
            if(it.currentBalance > 1) {
                percent = (changeAmount *100 /it.currentBalance).toInt()
            }

            var messageStatus = false

            if(it.monitorPercent != null ) {
                if(percent.absoluteValue > it.monitorPercent)
                    messageStatus = true
            }

            if(it.monitorAmount != null) {
                if(changeAmount.absoluteValue > it.monitorAmount)
                    messageStatus = true
            }

            if(messageStatus) {
                var  transferAmount = WiccUtils.convert(changeAmount).setScale(3, ROUND_UP)
                var message = "发生大额转入, 钱包地址: ${it.address},高度：${it.heigh} 到 ${currentNumber} 转入金额为:$transferAmount, 转入环比:${percent}%"
                if(changeAmount < 0)
                    message = "发生大额转出, 钱包地址: ${it.address},高度：${it.heigh} 到 ${currentNumber} 转出金额为:${-transferAmount}, 转出环比:${percent}%"
                XxlJobLogger.log("发送大额转出交易: $message")
                dingTalkService.sendTextMessage(sendUrl!!, message)
                var bcWiccAlertLog = BcWiccAlertLog()
                bcWiccAlertLog.address = it.address
                bcWiccAlertLog.notifyLinkUrl=  sendUrl
                bcWiccAlertLog.alertInfo = message
            }
            it.currentBalance = accountInfo.balance!!.toLong()
            it.heigh = currentNumber
            bcWiccMonitorAddressRepository.save(it)
        }
    }

    @Autowired lateinit var bcWiccMonitorAddressRepository: BcWiccMonitorAddressRepository
    @Autowired lateinit var sysChainMsgNotifySettingRepository: SysChainMsgNotifySettingRepository
    @Autowired lateinit var dingTalkService:DingTalkService
    @Autowired lateinit var bcWiccBlockService:BcWiccBlockService
    private val logger = LoggerFactory.getLogger(javaClass)
}