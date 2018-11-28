package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccAccountCheckLog

interface BcWiccAccountCheckLogService {
    fun getById(id:Long): BcWiccAccountCheckLog?

    fun save(bcWiccAccountCheckLog:BcWiccAccountCheckLog): BcWiccAccountCheckLog
}
