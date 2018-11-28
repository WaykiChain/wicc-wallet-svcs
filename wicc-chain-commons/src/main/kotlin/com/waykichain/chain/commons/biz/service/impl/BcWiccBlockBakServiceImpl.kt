package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.biz.domain.BcWiccBlockBak
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccBlockBakRepository
import com.waykichain.chain.commons.biz.service.BcWiccBlockBakService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BcWiccBlockBakServiceImpl: BcWiccBlockBakService {

    override fun getById(id:Long): BcWiccBlockBak? {
        return bcWiccBlockBakRepository.findOne(id)
    }

    override fun save(bcWiccBlockBak: BcWiccBlockBak): BcWiccBlockBak {
        return bcWiccBlockBakRepository.saveAndFlush(bcWiccBlockBak)
    }

    @Autowired lateinit var bcWiccBlockBakRepository: BcWiccBlockBakRepository
}
