package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.RollbackBlockBak

interface RollbackBlockBakService {

    fun getById(id:Long): RollbackBlockBak?

    fun save(rollbackBlockBak:RollbackBlockBak): RollbackBlockBak

    fun save(list: List<RollbackBlockBak>): MutableList<RollbackBlockBak>?

    fun queryByHeightAndStatus(height: Int, status: Int): RollbackBlockBak?

    fun queryByStatus(status: Int): MutableIterable<RollbackBlockBak>?
}
