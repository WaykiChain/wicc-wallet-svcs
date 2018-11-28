package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccWalletAccount
import com.waykichain.chain.biz.domain.BcWiccWalletAccountLog
import java.util.*

interface BcWiccWalletAccountService {
    fun getById(id:Long): BcWiccWalletAccount?

    fun save(bcWiccWalletAccount: BcWiccWalletAccount): BcWiccWalletAccount

    fun find(address: String, symbol: String): BcWiccWalletAccount?

    fun addBalance(
            address: String,
            coinSymbol: String,
            availableAmount: Long,
            fees:Long,
            txType:String,
            txid:String,
            number:Int,
            confirmedAt: Date,
            remark: String?): BcWiccWalletAccountLog
}
