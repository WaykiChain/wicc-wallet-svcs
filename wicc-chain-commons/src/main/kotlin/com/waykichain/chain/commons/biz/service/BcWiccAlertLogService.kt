package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccAlertLog

interface BcWiccAlertLogService {
    fun getById(id:Long): BcWiccAlertLog?

    fun save(bcWiccAlertLog:BcWiccAlertLog): BcWiccAlertLog
}
