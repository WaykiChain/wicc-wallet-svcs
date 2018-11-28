package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.commons.biz.service.BcWiccAccountAjustLogService
import com.waykichain.chain.biz.domain.BcWiccAccountAjustLog
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccAccountAjustLogRepository
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class BcWiccAccountAjustLogServiceImpl: BcWiccAccountAjustLogService {

    override fun getById(id:Long): BcWiccAccountAjustLog? {
        return bcWiccAccountAjustLogRepository.findOne(id)
    }

    override fun save(bcWiccAccountAjustLog:BcWiccAccountAjustLog): BcWiccAccountAjustLog {
        return bcWiccAccountAjustLogRepository.saveAndFlush(bcWiccAccountAjustLog)
    }

    @Autowired lateinit var bcWiccAccountAjustLogRepository: BcWiccAccountAjustLogRepository
}
