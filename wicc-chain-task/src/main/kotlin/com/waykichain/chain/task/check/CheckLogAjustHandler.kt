package com.waykichain.chain.task.check

import com.waykichain.chain.biz.domain.QBcWiccAccountCheckBatch
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccAccountCheckBatchRepository
import com.waykichain.chain.commons.biz.xservice.AccountCheckXService
import com.waykichain.chain.dict.AccountCheckBatchStatus
import com.xxl.job.core.biz.model.ReturnT
import com.xxl.job.core.handler.IJobHandler
import com.xxl.job.core.handler.annotation.JobHandler
import com.xxl.job.core.log.XxlJobLogger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Joss
 * @date 2018/4/8
 */
@Service
@JobHandler(value = "checkLogAjustHandler")
class CheckLogAjustHandler : IJobHandler() {

    override fun execute(param: String): ReturnT<String> {
        XxlJobLogger.log("生成logger checkLogAjustHandler开始")
        val batches = bcWiccAccountCheckBatchRepository.findAll(
                QBcWiccAccountCheckBatch.bcWiccAccountCheckBatch.status.eq(AccountCheckBatchStatus.CHECK_FINISHED.code))
        var batch = batches.firstOrNull()

        XxlJobLogger.log("batchId是:${batch!!.id}")

        accountCheckXService.onAjustAccountLog(batch)

        XxlJobLogger.log("生成logger checkLogAjustHandler开始结束")
        return ReturnT.SUCCESS

    }



    val logger = LoggerFactory.getLogger(javaClass)
    @Autowired
    lateinit var bcWiccAccountCheckBatchRepository: BcWiccAccountCheckBatchRepository

    @Autowired
    lateinit var accountCheckXService: AccountCheckXService
}