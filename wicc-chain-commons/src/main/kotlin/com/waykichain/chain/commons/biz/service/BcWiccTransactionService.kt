package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccTransaction
import com.waykichain.chain.commons.biz.cond.QueryTransactionCond

interface BcWiccTransactionService {

    fun getById(id:Long): BcWiccTransaction?

    fun save(bcWiccTransaction: BcWiccTransaction): BcWiccTransaction

    fun save(bcWiccTransactions:List<BcWiccTransaction>)

    fun getByTxid(txid: String): BcWiccTransaction

    fun getByAddress(queryTransactionCond: QueryTransactionCond): List<BcWiccTransaction>?

    fun getByAddressV2(queryTransactionCond: QueryTransactionCond):  Triple<List<BcWiccTransaction>, Int, Int>

    fun getByHeight(queryTransactionCond: QueryTransactionCond): List<BcWiccTransaction>?

    fun getByBlockNumber(height: Int): List<BcWiccTransaction>?

    fun delete(bcWiccTransaction: BcWiccTransaction)

    fun deleteBatch(list: List<BcWiccTransaction>)

}
