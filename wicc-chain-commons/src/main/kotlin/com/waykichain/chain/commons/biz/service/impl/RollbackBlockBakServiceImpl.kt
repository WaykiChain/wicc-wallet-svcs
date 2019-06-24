package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.biz.domain.QRollbackBlockBak
import com.waykichain.chain.commons.biz.service.RollbackBlockBakService
import com.waykichain.chain.biz.domain.RollbackBlockBak
import com.waykichain.chain.commons.biz.repository.mysql.RollbackBlockBakRepository
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class RollbackBlockBakServiceImpl: RollbackBlockBakService {

    override fun getById(id:Long): RollbackBlockBak? {
        return rollbackBlockBakRepository.findOne(id)
    }

    override fun save(rollbackBlockBak:RollbackBlockBak): RollbackBlockBak {
        return rollbackBlockBakRepository.saveAndFlush(rollbackBlockBak)
    }

    override fun save(list: List<RollbackBlockBak>): MutableList<RollbackBlockBak>? {
        return rollbackBlockBakRepository.save(list)
    }

    override fun queryByHeightAndStatus(height: Int, status: Int): RollbackBlockBak? {
        return rollbackBlockBakRepository.findOne(QRollbackBlockBak.rollbackBlockBak.height.eq(height).and(QRollbackBlockBak.rollbackBlockBak.status.eq(status)))
    }

    override fun queryByStatus(status: Int): MutableIterable<RollbackBlockBak>? {
        return rollbackBlockBakRepository.findAll(QRollbackBlockBak.rollbackBlockBak.status.eq(status))

    }

    @Autowired lateinit var rollbackBlockBakRepository: RollbackBlockBakRepository
}
