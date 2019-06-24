package com.waykichain.chain.commons.biz.xservice.impl

import com.waykichain.chain.commons.biz.service.BcWiccOfflineTransacationLogService
import com.waykichain.chain.commons.biz.service.BcWiccSendTransactionLogService
import com.waykichain.chain.commons.biz.xservice.BcWiccSendTransactionLogXService
import com.waykichain.commons.util.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 *@author zengdq
 *@date 2018/11/16 20:55
 */
@Service
class BcWiccSendTransactionLogXServiceImpl: BcWiccSendTransactionLogXService {
    override fun updateBcWiccSendTransactionLogRequestUuid(uuid: String) {
        var bcWiccSendTransactionLog =  bcWiccSendTransactionLogService.getByRequestUuid(uuid)
        var bcWiccOfflineTransactionLog = bcWiccOfflineTransactionLogService.getByRequestUuid(uuid)
        if(bcWiccSendTransactionLog != null){
            var updateUuid = uuid + "_error_"+ DateUtils.getTimeStamp(Date())
            bcWiccSendTransactionLog.requestUuid = updateUuid
            bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
        }
        if(bcWiccOfflineTransactionLog != null){
            var updateOfflineUuid = uuid + "_error_"+ DateUtils.getTimeStamp(Date())
            bcWiccOfflineTransactionLog.requestUuid = updateOfflineUuid
            bcWiccOfflineTransactionLogService.save(bcWiccOfflineTransactionLog)
        }
    }

    @Autowired
    lateinit var bcWiccSendTransactionLogService: BcWiccSendTransactionLogService

    @Autowired
    lateinit var bcWiccOfflineTransactionLogService: BcWiccOfflineTransacationLogService

}