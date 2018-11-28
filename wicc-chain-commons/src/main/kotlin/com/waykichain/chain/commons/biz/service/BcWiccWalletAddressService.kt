package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccWalletAddress

interface BcWiccWalletAddressService {
    fun getById(id:Long): BcWiccWalletAddress?

    fun save(bcWiccWalletAddress: BcWiccWalletAddress): BcWiccWalletAddress
}
