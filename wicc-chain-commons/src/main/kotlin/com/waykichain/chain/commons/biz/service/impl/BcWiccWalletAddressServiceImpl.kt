package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.biz.domain.BcWiccWalletAddress
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccWalletAddressRepository
import com.waykichain.chain.commons.biz.service.BcWiccWalletAddressService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BcWiccWalletAddressServiceImpl: BcWiccWalletAddressService {

    override fun getById(id:Long): BcWiccWalletAddress? {
        return bcWiccWalletAddressRepository.findOne(id)
    }

    override fun save(bcWiccWalletAddress: BcWiccWalletAddress): BcWiccWalletAddress {
        return bcWiccWalletAddressRepository.save(bcWiccWalletAddress)
    }

    @Autowired lateinit var bcWiccWalletAddressRepository: BcWiccWalletAddressRepository
}
