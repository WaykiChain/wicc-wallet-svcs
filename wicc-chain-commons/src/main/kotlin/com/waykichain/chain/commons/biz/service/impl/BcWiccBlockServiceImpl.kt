package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.biz.domain.BcWiccBlock
import com.waykichain.chain.biz.domain.QBcWiccBlock
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccBlockRepository
import com.waykichain.chain.commons.biz.service.BcWiccBlockService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.querydsl.QPageRequest
import org.springframework.data.querydsl.QSort
import org.springframework.stereotype.Service

@Service
class BcWiccBlockServiceImpl: BcWiccBlockService {

    override fun getById(id:Long): BcWiccBlock? {
        return bcWiccBlockRepository.findOne(id)
    }

    override fun save(bcWiccBlock: BcWiccBlock): BcWiccBlock {
        return bcWiccBlockRepository.save(bcWiccBlock)
    }

    override fun getLastBlockId(): Int? {

        var page = 0
        var size = 1
        val sort = QSort(QBcWiccBlock.bcWiccBlock.number.desc())
        val qPageRequest = QPageRequest(page, size, sort)

        val lastBcWiccBlock = bcWiccBlockRepository.findAll(null, qPageRequest)
        if (lastBcWiccBlock.content.isEmpty())
            return 0
        return lastBcWiccBlock.content.get(0).number.toInt()
    }
    @Autowired lateinit var bcWiccBlockRepository: BcWiccBlockRepository
}
