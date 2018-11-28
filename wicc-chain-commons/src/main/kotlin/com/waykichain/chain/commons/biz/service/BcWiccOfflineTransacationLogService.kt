package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccOfflineTransacationLog

interface BcWiccOfflineTransacationLogService {
    fun getById(id:Long): BcWiccOfflineTransacationLog?

    fun save(bcWiccOfflineTransacationLog: BcWiccOfflineTransacationLog): BcWiccOfflineTransacationLog
}
