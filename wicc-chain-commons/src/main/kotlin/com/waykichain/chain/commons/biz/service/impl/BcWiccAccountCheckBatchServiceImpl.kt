package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.commons.biz.service.BcWiccAccountCheckBatchService
import com.waykichain.chain.biz.domain.BcWiccAccountCheckBatch
import com.waykichain.chain.biz.domain.QBcWiccAccountCheckBatch
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccAccountCheckBatchRepository
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class BcWiccAccountCheckBatchServiceImpl: BcWiccAccountCheckBatchService {

    override fun getById(id:Long): BcWiccAccountCheckBatch? {
        return bcWiccAccountCheckBatchRepository.findOne(id)
    }

    override fun save(bcWiccAccountCheckBatch:BcWiccAccountCheckBatch): BcWiccAccountCheckBatch {
        return bcWiccAccountCheckBatchRepository.saveAndFlush(bcWiccAccountCheckBatch)
    }

    @Autowired lateinit var bcWiccAccountCheckBatchRepository: BcWiccAccountCheckBatchRepository
}
