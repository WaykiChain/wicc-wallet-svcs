package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccMonitorAddress

interface BcWiccMonitorAddressService {
    fun getById(id:Long): BcWiccMonitorAddress?

    fun save(bcWiccMonitorAddress:BcWiccMonitorAddress): BcWiccMonitorAddress
}
