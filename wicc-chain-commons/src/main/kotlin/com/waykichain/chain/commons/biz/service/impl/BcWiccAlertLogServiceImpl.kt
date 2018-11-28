package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.commons.biz.service.BcWiccAlertLogService
import com.waykichain.chain.biz.domain.BcWiccAlertLog
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccAlertLogRepository
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class BcWiccAlertLogServiceImpl: BcWiccAlertLogService {

    override fun getById(id:Long): BcWiccAlertLog? {
        return bcWiccAlertLogRepository.findOne(id)
    }

    override fun save(bcWiccAlertLog:BcWiccAlertLog): BcWiccAlertLog {
        return bcWiccAlertLogRepository.saveAndFlush(bcWiccAlertLog)
    }

    @Autowired lateinit var bcWiccAlertLogRepository: BcWiccAlertLogRepository
}
