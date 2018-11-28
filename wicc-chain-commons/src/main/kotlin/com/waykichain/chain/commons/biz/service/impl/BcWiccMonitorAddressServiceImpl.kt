package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.commons.biz.service.BcWiccMonitorAddressService
import com.waykichain.chain.biz.domain.BcWiccMonitorAddress
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccMonitorAddressRepository
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class BcWiccMonitorAddressServiceImpl: BcWiccMonitorAddressService {

    override fun getById(id:Long): BcWiccMonitorAddress? {
        return bcWiccMonitorAddressRepository.findOne(id)
    }

    override fun save(bcWiccMonitorAddress:BcWiccMonitorAddress): BcWiccMonitorAddress {
        return bcWiccMonitorAddressRepository.saveAndFlush(bcWiccMonitorAddress)
    }

    @Autowired lateinit var bcWiccMonitorAddressRepository: BcWiccMonitorAddressRepository
}
