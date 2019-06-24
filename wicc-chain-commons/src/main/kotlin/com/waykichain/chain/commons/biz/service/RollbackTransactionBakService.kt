package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.RollbackTransactionBak

interface RollbackTransactionBakService {
    fun getById(id:Long): RollbackTransactionBak?

    fun save(rollbackTransactionBak:RollbackTransactionBak): RollbackTransactionBak
}
