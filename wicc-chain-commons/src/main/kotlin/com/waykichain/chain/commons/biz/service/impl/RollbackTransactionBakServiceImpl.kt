package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.commons.biz.service.RollbackTransactionBakService
import com.waykichain.chain.biz.domain.RollbackTransactionBak
import com.waykichain.chain.commons.biz.repository.mysql.RollbackTransactionBakRepository
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class RollbackTransactionBakServiceImpl: RollbackTransactionBakService {

    override fun getById(id:Long): RollbackTransactionBak? {
        return rollbackTransactionBakRepository.findOne(id)
    }

    override fun save(rollbackTransactionBak:RollbackTransactionBak): RollbackTransactionBak {
        return rollbackTransactionBakRepository.saveAndFlush(rollbackTransactionBak)
    }

    @Autowired lateinit var rollbackTransactionBakRepository: RollbackTransactionBakRepository
}
