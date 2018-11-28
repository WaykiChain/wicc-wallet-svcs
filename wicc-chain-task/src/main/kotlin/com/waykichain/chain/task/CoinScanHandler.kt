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
@JobHandler(value = "coinScanHandler")
class CoinScanHandler : IJobHandler() {

    /**
     * @param params
     *      params.first() 统计时间长度
     *      params.last()  刷新频率
     */
    override fun execute(params: String?): ReturnT<String> {

        params!!.split(',').forEach {
            XxlJobLogger.log("开始扫描${it}块")

            val handler = CoinHandler.getHandler(it!!)
            handler!!.scanBlock()

            XxlJobLogger.log("结束扫描${it}块")
        }

        return ReturnT.SUCCESS
    }

    private val logger = LoggerFactory.getLogger(javaClass)
}