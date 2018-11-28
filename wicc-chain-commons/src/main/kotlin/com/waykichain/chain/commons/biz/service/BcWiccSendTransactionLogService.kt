package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccSendTransactionLog

interface BcWiccSendTransactionLogService {
    fun getById(id:Long): BcWiccSendTransactionLog?

    fun save(bcWiccSendTransactionLog: BcWiccSendTransactionLog): BcWiccSendTransactionLog

    fun getByRequestUuid(uuid: String): BcWiccSendTransactionLog?

}
