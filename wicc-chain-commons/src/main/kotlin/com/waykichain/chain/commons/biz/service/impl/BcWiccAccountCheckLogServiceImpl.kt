package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.commons.biz.service.BcWiccAccountCheckLogService
import com.waykichain.chain.biz.domain.BcWiccAccountCheckLog
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccAccountCheckLogRepository
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class BcWiccAccountCheckLogServiceImpl: BcWiccAccountCheckLogService {

    override fun getById(id:Long): BcWiccAccountCheckLog? {
        return bcWiccAccountCheckLogRepository.findOne(id)
    }

    override fun save(bcWiccAccountCheckLog:BcWiccAccountCheckLog): BcWiccAccountCheckLog {
        return bcWiccAccountCheckLogRepository.saveAndFlush(bcWiccAccountCheckLog)
    }

    @Autowired lateinit var bcWiccAccountCheckLogRepository: BcWiccAccountCheckLogRepository
}
