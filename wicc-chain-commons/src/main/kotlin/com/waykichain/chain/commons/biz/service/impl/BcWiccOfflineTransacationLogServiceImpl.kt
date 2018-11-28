package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.biz.domain.BcWiccOfflineTransacationLog
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccOfflineTransacationLogRepository
import com.waykichain.chain.commons.biz.service.BcWiccOfflineTransacationLogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class   BcWiccOfflineTransacationLogServiceImpl: BcWiccOfflineTransacationLogService {

    override fun getById(id:Long): BcWiccOfflineTransacationLog? {
        return bcWiccOfflineTransacationLogRepository.findOne(id)
    }

    override fun save(bcWiccOfflineTransacationLog: BcWiccOfflineTransacationLog): BcWiccOfflineTransacationLog {
        return bcWiccOfflineTransacationLogRepository.saveAndFlush(bcWiccOfflineTransacationLog)
    }

    @Autowired lateinit var bcWiccOfflineTransacationLogRepository: BcWiccOfflineTransacationLogRepository
}
