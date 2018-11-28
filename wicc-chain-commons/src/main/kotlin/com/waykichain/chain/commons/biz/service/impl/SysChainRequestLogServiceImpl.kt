package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.commons.biz.service.SysChainRequestLogService
import com.waykichain.chain.biz.domain.SysChainRequestLog
import com.waykichain.chain.commons.biz.repository.mysql.SysChainRequestLogRepository
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class SysChainRequestLogServiceImpl: SysChainRequestLogService {

    override fun getById(id:Long): SysChainRequestLog? {
        return sysChainRequestLogRepository.findOne(id)
    }

    override fun save(sysChainRequestLog:SysChainRequestLog): SysChainRequestLog {
        return sysChainRequestLogRepository.saveAndFlush(sysChainRequestLog)
    }

    @Autowired lateinit var sysChainRequestLogRepository: SysChainRequestLogRepository
}
