package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccBlock

interface BcWiccBlockService {
    fun getById(id:Long): BcWiccBlock?

    fun getLastBlockId():Int?

    fun getByHeight(height: Int): BcWiccBlock?

    fun save(bcWiccBlock: BcWiccBlock): BcWiccBlock
}
