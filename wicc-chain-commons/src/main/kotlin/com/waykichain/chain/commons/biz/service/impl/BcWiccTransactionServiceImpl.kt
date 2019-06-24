package com.waykichain.chain.commons.biz.service.impl

import com.querydsl.core.types.dsl.BooleanExpression
import com.waykichain.chain.biz.domain.BcWiccTransaction
import com.waykichain.chain.biz.domain.QBcWiccTransaction
import com.waykichain.chain.commons.biz.cond.QueryTransactionCond
import com.waykichain.chain.commons.biz.dict.TransactionDirectionDict
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccTransactionRepository
import com.waykichain.chain.commons.biz.service.BcWiccTransactionService
import org.apache.commons.lang3.StringUtils
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service

@Service
class BcWiccTransactionServiceImpl: BcWiccTransactionService {

    override fun getByAddress(queryTransactionCond: QueryTransactionCond): List<BcWiccTransaction>? {

        var predicate = QBcWiccTransaction.bcWiccTransaction.addr.eq(queryTransactionCond.address)
                .or( QBcWiccTransaction.bcWiccTransaction.desaddr.eq(queryTransactionCond.address))
        if (null != queryTransactionCond.startNumber) {
            predicate = predicate.and(QBcWiccTransaction.bcWiccTransaction.height.gt(queryTransactionCond.startNumber))
        }
        val sort = Sort(Sort.Direction.DESC, "confirmedTime")
        return bcWiccTransactionRepository.findAll(predicate, sort).toList()
    }

     override fun getByAddressV2(queryTransactionCond: QueryTransactionCond): Triple<List<BcWiccTransaction>, Int, Int> {

        var predicate: BooleanExpression? = null
        /** 只查入账*/
        if (TransactionDirectionDict.TRAN_DIRECTION_IN.numValue == queryTransactionCond.tranDirection ) {
            predicate = QBcWiccTransaction.bcWiccTransaction.addr.eq(queryTransactionCond.address)
        /** 只查出账*/
        } else if (TransactionDirectionDict.TRAN_DIRECTION_OUT.numValue == queryTransactionCond.tranDirection ) {
            predicate = QBcWiccTransaction.bcWiccTransaction.desaddr.eq(queryTransactionCond.address)
        } else {
            predicate = QBcWiccTransaction.bcWiccTransaction.addr.eq(queryTransactionCond.address)
                    .or( QBcWiccTransaction.bcWiccTransaction.desaddr.eq(queryTransactionCond.address))
        }
        if(StringUtils.isNotBlank(queryTransactionCond.txtype))  predicate = predicate.and(QBcWiccTransaction.bcWiccTransaction.txType.eq(queryTransactionCond.txtype))
        val sort = Sort(Sort.Direction.DESC, "confirmedTime")
        if (null != queryTransactionCond.startNumber) {
            predicate = predicate.and(QBcWiccTransaction.bcWiccTransaction.height.gt(queryTransactionCond.startNumber))
        }
        val pageRequest = PageRequest(queryTransactionCond.currentPage-1, queryTransactionCond.pageSize, sort)
        var list =  bcWiccTransactionRepository.findAll(predicate, pageRequest).toList()
        val totalCount = bcWiccTransactionRepository.count(predicate).toInt()
        var totalPages = if (totalCount == 0) {0} else if (totalCount % queryTransactionCond.pageSize == 0) { totalCount/queryTransactionCond.pageSize } else { totalCount/queryTransactionCond.pageSize + 1 }
        return Triple(list, totalPages, totalCount)
    }

    override fun getByBlockNumber(blockNumber: Int): List<BcWiccTransaction>? {

        var predicate = QBcWiccTransaction.bcWiccTransaction.height.eq(blockNumber)
        val sort = Sort(Sort.Direction.DESC, "confirmedTime")
        return bcWiccTransactionRepository.findAll(predicate, sort).toList()
    }

    override fun getByHeight(queryTransactionCond: QueryTransactionCond): List<BcWiccTransaction>? {

        var predicate = QBcWiccTransaction.bcWiccTransaction.height.gt(queryTransactionCond.startNumber);
        val sort = Sort(Sort.Direction.DESC, "confirmedTime")
        return bcWiccTransactionRepository.findAll(predicate, sort).toList()
    }

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

    override fun delete(bcWiccTransaction: BcWiccTransaction) {
        bcWiccTransactionRepository.delete(bcWiccTransaction)
    }

    override fun deleteBatch(list: List<BcWiccTransaction>) {
        bcWiccTransactionRepository.deleteInBatch(list)
    }

    @Autowired lateinit var bcWiccTransactionRepository: BcWiccTransactionRepository
}
