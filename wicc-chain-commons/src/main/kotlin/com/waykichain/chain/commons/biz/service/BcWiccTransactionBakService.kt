package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccTransactionBak

interface BcWiccTransactionBakService {
    fun getById(id:Long): BcWiccTransactionBak?

    fun save(bcWiccTransactionBak: BcWiccTransactionBak): BcWiccTransactionBak
}
