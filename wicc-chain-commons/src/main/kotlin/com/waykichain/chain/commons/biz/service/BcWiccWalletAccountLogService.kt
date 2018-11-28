package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccWalletAccountLog

interface BcWiccWalletAccountLogService {
    fun getById(id:Long): BcWiccWalletAccountLog?

    fun save(bcWiccWalletAccountLog: BcWiccWalletAccountLog): BcWiccWalletAccountLog

    fun getLastLogInfo(address: String?): BcWiccWalletAccountLog?
}
