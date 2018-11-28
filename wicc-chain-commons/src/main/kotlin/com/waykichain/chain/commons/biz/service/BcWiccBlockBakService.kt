package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccBlockBak

interface BcWiccBlockBakService {
    fun getById(id:Long): BcWiccBlockBak?

    fun save(bcWiccBlockBak: BcWiccBlockBak): BcWiccBlockBak
}
