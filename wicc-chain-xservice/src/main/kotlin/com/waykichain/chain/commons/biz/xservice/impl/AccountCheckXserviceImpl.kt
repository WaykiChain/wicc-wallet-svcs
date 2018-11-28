package com.waykichain.chain.commons.biz.xservice.impl

import com.waykichain.chain.biz.domain.*
import com.waykichain.chain.commons.biz.dict.BcWiccAccountCheckBatchStatus
import com.waykichain.chain.commons.biz.dict.BcWiccAccountCheckLogStatus
import com.waykichain.chain.commons.biz.dict.CoinType
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccAccountCheckLogRepository
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccWalletAccountRepository
import com.waykichain.chain.commons.biz.service.BcWiccAccountCheckBatchService
import com.waykichain.chain.commons.biz.service.BcWiccAccountCheckLogService
import com.waykichain.chain.commons.biz.service.BcWiccWalletAccountService
import com.waykichain.chain.commons.biz.xservice.AccountCheckXService
import com.waykichain.chain.commons.biz.xservice.CoinHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.querydsl.QSort
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.Executors

/**
 * Created by Joss on 05/16/18.
 */

@Service("accountCheckXService")
open class AccountCheckXserviceImpl :AccountCheckXService {

    var countPerTime:Int = 500

    override fun onAjustAccountLog(batch: BcWiccAccountCheckBatch?) {

        batch!!.status =  BcWiccAccountCheckBatchStatus.AJUSTING.type
        bcWiccAccountCheckBatchService.save(batch)

        var index = 0

        while (true) {
            var checkLogs = getCheckLogByPageIndex(batch!!.id!!, index, countPerTime)
            if (checkLogs == null || checkLogs!!.size == 0)
                return

            onAjustAccountLog(checkLogs!!)
            index = index +1
        }
    }

    fun onAjustAccountLog(checkLogs:List<BcWiccAccountCheckLog>) {
        checkLogs.forEach {
            if (it.status == BcWiccAccountCheckBatchStatus.SUBMIT.type) {

                val update = bcWiccAccountCheckLogRepository.updateByLockStatus(
                        it.id,
                        BcWiccAccountCheckLogStatus.DAIL_FINISHED.type,
                        BcWiccAccountCheckLogStatus.SUBMIT.type)
                if (update == 1) {
                    bcWiccWalletAccountService.addBalance(it.address, it.coinSymbol, it.diffAmount, 0L, "SYSTEM_UPDATE", "${it.batchId} -${it.id}", 0, Date(), "系统调整")
                } else {
                    logger.error("onAjustAccountLog Id: ${it.id} 更新状态失败: ${it.status}")
                }
            }
        }
    }


    fun getCheckLogByPageIndex(batchId:Long, index:Int, count:Int): List<BcWiccAccountCheckLog>? {
        val qSort = QSort(QBcWiccAccountCheckLog.bcWiccAccountCheckLog.id.asc())
        var cond = QBcWiccAccountCheckLog.bcWiccAccountCheckLog.batchId.eq(batchId).and(
                QBcWiccAccountCheckLog.bcWiccAccountCheckLog.diffAmount.ne(0L))
        val page = bcWiccAccountCheckLogRepository.findAll(cond, PageRequest(index, count, qSort))
        if(page !=null && page.content!= null)
            return  page.content.toList()
        return null

    }

    override fun onCheck(bcWiccAccountCheckBatch: BcWiccAccountCheckBatch) {
        /**
         * 分页获取信息
         */

        bcWiccAccountCheckBatch.status =  BcWiccAccountCheckBatchStatus.WAITING.type
        bcWiccAccountCheckBatchService.save(bcWiccAccountCheckBatch)
        var index = 0
        val executorService = Executors.newFixedThreadPool(20)

        while (true) {
            var accounts = getAccountByPageIndex(index, countPerTime)
            if (accounts == null || accounts!!.size == 0)
                return

            index = index+ 1
            executorService.execute {checkAccountLog(bcWiccAccountCheckBatch, accounts!!)}
        }
    }

    fun checkAccountLog(bcWiccAccountCheckBatch: BcWiccAccountCheckBatch, accounts: List<BcWiccWalletAccount>) {
        accounts.forEach {
            val accountInfoVO = CoinHandler.getHandler(CoinType.WICC.symbol)!!.getAccountInfo(it.address)
            var bcWiccAccountCheckLog = BcWiccAccountCheckLog()
            bcWiccAccountCheckLog.eod = bcWiccAccountCheckBatch.eod
            bcWiccAccountCheckLog.batchId = bcWiccAccountCheckBatch.id!!
            bcWiccAccountCheckLog.address  = it.address
            bcWiccAccountCheckLog.type = it.type
            bcWiccAccountCheckLog.coinSymbol = it.coinSymbol
            bcWiccAccountCheckLog.chainBalance = accountInfoVO!!.balance!!.toLong()
            bcWiccAccountCheckLog.balance =  it.balance
            bcWiccAccountCheckLog.diffAmount = bcWiccAccountCheckLog.chainBalance - bcWiccAccountCheckLog.balance
            if(bcWiccAccountCheckLog.diffAmount == 0L) {
                bcWiccAccountCheckLog.status = BcWiccAccountCheckLogStatus.NORMAL.type
            } else {
                bcWiccAccountCheckLog.status = BcWiccAccountCheckLogStatus.SUBMIT.type
            }
            bcWiccAccountCheckLogService.save(bcWiccAccountCheckLog)
        }
    }

    fun getAccountByPageIndex(index:Int, count:Int): List<BcWiccWalletAccount>? {
        val qSort = QSort(QBcWiccWalletAccount.bcWiccWalletAccount.id.asc())
        val page = bcWiccWalletAccountRepository.findAll(PageRequest(index, count, qSort))
        if(page !=null && page.content!= null)
            return  page.content.toList()
        return null

    }

    private val logger = LoggerFactory.getLogger(javaClass)
    @Autowired lateinit var bcWiccWalletAccountRepository: BcWiccWalletAccountRepository
    @Autowired lateinit var bcWiccAccountCheckLogRepository: BcWiccAccountCheckLogRepository
    @Autowired lateinit var bcWiccAccountCheckLogService: BcWiccAccountCheckLogService
    @Autowired lateinit var bcWiccWalletAccountService: BcWiccWalletAccountService
    @Autowired lateinit var bcWiccAccountCheckBatchService: BcWiccAccountCheckBatchService

}