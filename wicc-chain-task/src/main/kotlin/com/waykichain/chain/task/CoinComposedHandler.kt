package com.waykichain.chain.task

import com.waykichain.chain.commons.biz.xservice.CoinHandler
import com.xxl.job.core.biz.model.ReturnT
import com.xxl.job.core.handler.IJobHandler
import com.xxl.job.core.handler.annotation.JobHandler
import com.xxl.job.core.log.XxlJobLogger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * Created by Joss
 * @date 2018/4/8
 */
@Service
@JobHandler(value = "coinComposedHandler")
class CoinComposedHandler : IJobHandler() {

    override fun execute(param: String): ReturnT<String> {
        param.split(",").forEach {
        XxlJobLogger.log("归集[${it}]钱包")

        val handler = CoinHandler.getHandler(it!!)
        handler!!.composed()

        XxlJobLogger.log("归集[${it}]钱包")
    }

    return ReturnT.SUCCESS

    }

    private val logger = LoggerFactory.getLogger(javaClass)
}