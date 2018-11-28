package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccTransaction

interface BcWiccTransactionService {

    fun getById(id:Long): BcWiccTransaction?

    fun save(bcWiccTransaction: BcWiccTransaction): BcWiccTransaction

    fun save(bcWiccTransactions:List<BcWiccTransaction>)

    fun getByTxid(txid: String): BcWiccTransaction

}
