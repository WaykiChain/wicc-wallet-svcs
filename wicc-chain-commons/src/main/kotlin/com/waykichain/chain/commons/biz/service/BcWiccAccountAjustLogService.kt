package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccAccountAjustLog

interface BcWiccAccountAjustLogService {
    fun getById(id:Long): BcWiccAccountAjustLog?

    fun save(bcWiccAccountAjustLog:BcWiccAccountAjustLog): BcWiccAccountAjustLog
}
