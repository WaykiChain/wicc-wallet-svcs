package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.biz.domain.BcWiccTransactionBak
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccTransactionBakRepository
import com.waykichain.chain.commons.biz.service.BcWiccTransactionBakService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BcWiccTransactionBakServiceImpl: BcWiccTransactionBakService {

    override fun getById(id:Long): BcWiccTransactionBak? {
        return bcWiccTransactionBakRepository.findOne(id)
    }

    override fun save(bcWiccTransactionBak: BcWiccTransactionBak): BcWiccTransactionBak {
        return bcWiccTransactionBakRepository.saveAndFlush(bcWiccTransactionBak)
    }

    @Autowired lateinit var bcWiccTransactionBakRepository: BcWiccTransactionBakRepository
}
