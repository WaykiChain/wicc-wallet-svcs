package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.biz.domain.BcWiccWalletAccount
import com.waykichain.chain.biz.domain.BcWiccWalletAccountLog
import com.waykichain.chain.biz.domain.QBcWiccWalletAccount
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccWalletAccountRepository
import com.waykichain.chain.commons.biz.service.BcWiccWalletAccountLogService
import com.waykichain.chain.commons.biz.service.BcWiccWalletAccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BcWiccWalletAccountServiceImpl: BcWiccWalletAccountService {

    override fun getById(id:Long): BcWiccWalletAccount? {
        return bcWiccWalletAccountRepository.findOne(id)
    }

    override fun save(bcWiccWalletAccount: BcWiccWalletAccount): BcWiccWalletAccount {
        return bcWiccWalletAccountRepository.save(bcWiccWalletAccount)
    }

    override fun addBalance(
            address: String,
            coinSymbol: String,
            availableAmount: Long,
            fees:Long,
            txType:String,
            txid:String,
            number:Int,
            confirmedAt: Date,
            remark: String?): BcWiccWalletAccountLog {

        var bcWiccWalletAccount = find(address, coinSymbol)
        if (bcWiccWalletAccount == null) {
            bcWiccWalletAccount = BcWiccWalletAccount()
            bcWiccWalletAccount.coinSymbol = coinSymbol
            bcWiccWalletAccount.address = address
            bcWiccWalletAccount.balance = 0L
            bcWiccWalletAccount = save(bcWiccWalletAccount)
        }

        val bcWiccWalletAccountLog = BcWiccWalletAccountLog()
        bcWiccWalletAccountLog.address = address
        bcWiccWalletAccountLog.txType  = txType
        bcWiccWalletAccountLog.coinSymbol = coinSymbol
        bcWiccWalletAccountLog.beforeBalance = bcWiccWalletAccount.balance!!
        bcWiccWalletAccountLog.availableAmount = availableAmount
        bcWiccWalletAccountLog.fees = fees
        bcWiccWalletAccountLog.afterBalance = bcWiccWalletAccount.balance!! + availableAmount  + fees  //  BigDecimal(availableAmount)
        bcWiccWalletAccountLog.remark = remark
        bcWiccWalletAccountLog.confirmedAt = confirmedAt
        bcWiccWalletAccountLog.txid = txid
        bcWiccWalletAccountLog.number = number

        bcWiccWalletAccount.balance = bcWiccWalletAccountLog.afterBalance
        save(bcWiccWalletAccount)

        val log = bcWiccWalletAccountLogService.save(bcWiccWalletAccountLog)
        return log
    }

    override fun find(address: String, symbol: String): BcWiccWalletAccount? {
        var predicate = QBcWiccWalletAccount.bcWiccWalletAccount.address.eq(address)
                .and(QBcWiccWalletAccount.bcWiccWalletAccount.coinSymbol.eq(symbol))
        return bcWiccWalletAccountRepository.findOne(predicate)
    }


    @Autowired lateinit var bcWiccWalletAccountRepository: BcWiccWalletAccountRepository
    @Autowired lateinit var bcWiccWalletAccountLogService: BcWiccWalletAccountLogService
}
