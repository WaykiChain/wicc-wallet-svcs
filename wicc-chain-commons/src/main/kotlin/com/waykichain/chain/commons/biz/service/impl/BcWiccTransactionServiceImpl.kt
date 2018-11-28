package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.biz.domain.BcWiccTransaction
import com.waykichain.chain.biz.domain.QBcWiccTransaction
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccTransactionRepository
import com.waykichain.chain.commons.biz.service.BcWiccTransactionService
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BcWiccTransactionServiceImpl: BcWiccTransactionService {

    var logger = Logger.getLogger(this.javaClass)
    override fun getByTxid(txid: String): BcWiccTransaction {
        var express = QBcWiccTransaction.bcWiccTransaction.txid.eq(txid)
        return bcWiccTransactionRepository.findOne(express)
    }

    override fun save(bcWiccTransactions: List<BcWiccTransaction>) {
        bcWiccTransactions.forEach {
            logger.info("transaction rawtx: " + it.rawtx)

        }
        bcWiccTransactionRepository.save(bcWiccTransactions)
    }

    override fun getById(id:Long): BcWiccTransaction? {
        return bcWiccTransactionRepository.findOne(id)
    }

    override fun save(bcWiccTransaction: BcWiccTransaction): BcWiccTransaction {
        return bcWiccTransactionRepository.save(bcWiccTransaction)
    }

    @Autowired lateinit var bcWiccTransactionRepository: BcWiccTransactionRepository
}
