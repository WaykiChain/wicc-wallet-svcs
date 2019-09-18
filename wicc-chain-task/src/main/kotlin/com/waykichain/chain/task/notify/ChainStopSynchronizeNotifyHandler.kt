package com.waykichain.chain.task.notify

import com.waykichain.chain.commons.biz.dict.CoinType
import com.waykichain.chain.commons.biz.xservice.CoinHandler
import com.xxl.job.core.biz.model.ReturnT
import com.xxl.job.core.handler.IJobHandler
import com.xxl.job.core.handler.annotation.JobHandler
import com.xxl.job.core.log.XxlJobLogger
import org.springframework.stereotype.Service

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/6/25 11:47
 *
 * @Description:    节点停止同步-监控
 *
 */
@Service
@JobHandler("chainStopSynchronizeNotifyHandler")
class ChainStopSynchronizeNotifyHandler: IJobHandler() {

    override fun execute(p0: String?): ReturnT<String> {
        XxlJobLogger.log("Chain node stop check start!")
        if (checkChainStop()){
            XxlJobLogger.log("Chain node stop check stop!")
            return SUCCESS
        }
        XxlJobLogger.log("Chain node stop check stop!")
        return FAIL
    }

    fun checkChainStop():Boolean {

        return try {
            val handler = CoinHandler.getHandler(CoinType.WICC.symbol)
            handler!!.checkChainStop()
        } catch (e: Exception) {
            XxlJobLogger.log("[Chain stop check] error!", e)
            false
        }
    }


}