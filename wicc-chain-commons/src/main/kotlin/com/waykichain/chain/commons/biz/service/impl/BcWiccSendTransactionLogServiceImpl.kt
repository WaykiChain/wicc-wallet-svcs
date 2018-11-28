package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.biz.domain.BcWiccSendTransactionLog
import com.waykichain.chain.biz.domain.QBcWiccSendTransactionLog
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccSendTransactionLogRepository
import com.waykichain.chain.commons.biz.service.BcWiccSendTransactionLogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BcWiccSendTransactionLogServiceImpl: BcWiccSendTransactionLogService {
    override fun getByRequestUuid(uuid: String): BcWiccSendTransactionLog? {
        return bcWiccSendTransactionLogRepository.findAll(QBcWiccSendTransactionLog.bcWiccSendTransactionLog.requestUuid.eq(uuid)).firstOrNull()
    }

    override fun getById(id:Long): BcWiccSendTransactionLog? {
        return bcWiccSendTransactionLogRepository.findOne(id)
    }

    override fun save(bcWiccSendTransactionLog: BcWiccSendTransactionLog): BcWiccSendTransactionLog {
        return bcWiccSendTransactionLogRepository.save(bcWiccSendTransactionLog)
    }

    @Autowired lateinit var bcWiccSendTransactionLogRepository: BcWiccSendTransactionLogRepository
}
