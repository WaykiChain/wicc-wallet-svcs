package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.biz.domain.BcWiccWalletAccountLog
import com.waykichain.chain.biz.domain.QBcWiccWalletAccountLog
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccWalletAccountLogRepository
import com.waykichain.chain.commons.biz.service.BcWiccWalletAccountLogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.querydsl.QPageRequest
import org.springframework.data.querydsl.QSort
import org.springframework.stereotype.Service

@Service
class BcWiccWalletAccountLogServiceImpl: BcWiccWalletAccountLogService {

    override fun getById(id:Long): BcWiccWalletAccountLog? {
        return bcWiccWalletAccountLogRepository.findOne(id)
    }

    override fun save(bcWiccWalletAccountLog: BcWiccWalletAccountLog): BcWiccWalletAccountLog {
        return bcWiccWalletAccountLogRepository.save(bcWiccWalletAccountLog)
    }

    override fun getLastLogInfo(address: String?): BcWiccWalletAccountLog? {
        var page = 0
        var size = 1
        val sort = QSort(QBcWiccWalletAccountLog.bcWiccWalletAccountLog.id.desc())
        val qPageRequest = QPageRequest(page, size, sort)

        var predcat = QBcWiccWalletAccountLog.bcWiccWalletAccountLog.address.eq(address)

        val lastBcWiccBlock = bcWiccWalletAccountLogRepository.findAll(predcat, qPageRequest)
        if (lastBcWiccBlock.content.isEmpty())
            return null
        return lastBcWiccBlock.content.get(0)
    }
    @Autowired lateinit var bcWiccWalletAccountLogRepository: BcWiccWalletAccountLogRepository
}
