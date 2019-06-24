package com.waykichain.chain.task.notify


import com.waykichain.chain.commons.biz.dict.CoinType
import com.waykichain.chain.commons.biz.xservice.CoinHandler
import com.xxl.job.core.biz.model.ReturnT
import com.xxl.job.core.handler.IJobHandler
import com.xxl.job.core.handler.annotation.JobHandler
import com.xxl.job.core.log.XxlJobLogger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
@JobHandler("rollbackNotifyHandler")
open class RollbackNotifyHandler : IJobHandler() {


    /**
     * @param params
     */
    override fun execute(params: String?): ReturnT<String> {
        XxlJobLogger.log("Roll back check start!")

        checkRollback()

        XxlJobLogger.log("Roll back check finish")

        return ReturnT.SUCCESS
    }

    fun checkRollback() {
        try {
            val handler = CoinHandler.getHandler(CoinType.WICC.symbol)
            handler!!.checkRollback()
        } catch (e: Exception) {
            logger.error("[scan chain rollback] Handler execute() error", e)

        }
    }

    private val logger = LoggerFactory.getLogger(javaClass)

}