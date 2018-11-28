package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccAccountCheckBatch

interface BcWiccAccountCheckBatchService {
    fun getById(id:Long): BcWiccAccountCheckBatch?


    fun save(bcWiccAccountCheckBatch:BcWiccAccountCheckBatch): BcWiccAccountCheckBatch
}
