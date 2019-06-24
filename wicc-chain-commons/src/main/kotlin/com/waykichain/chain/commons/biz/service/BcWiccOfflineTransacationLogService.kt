package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccOfflineTransacationLog
import com.waykichain.chain.biz.domain.BcWiccSendTransactionLog

interface BcWiccOfflineTransacationLogService {
    fun getById(id:Long): BcWiccOfflineTransacationLog?

    fun save(bcWiccOfflineTransacationLog: BcWiccOfflineTransacationLog): BcWiccOfflineTransacationLog

    fun getByRequestUuid(uuid: String): BcWiccOfflineTransacationLog?
}
