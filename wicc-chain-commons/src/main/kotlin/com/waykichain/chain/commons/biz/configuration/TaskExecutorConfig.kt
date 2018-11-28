package com.waykichain.chain.commons.biz.configuration

import com.xxl.job.core.executor.XxlJobExecutor
import com.waykichain.chain.commons.biz.env.Environment
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by richardchen on 8/22/17.
 */
@Configuration
@ComponentScan("com.waykichain")
open class TaskExecutorConfig {
    private val logger = LoggerFactory.getLogger(com.waykichain.chain.commons.biz.configuration.TaskExecutorConfig::class.java)


    open fun init(): XxlJobExecutor {
        logger.trace("------------ xxlJobExecutor -----------")

        val xxlJobExecutor = XxlJobExecutor()
        xxlJobExecutor.setIp(com.waykichain.chain.commons.biz.env.Environment.TASK_EXECUTOR_IP)
        xxlJobExecutor.setPort(Integer.parseInt(com.waykichain.chain.commons.biz.env.Environment.TASK_EXECUTOR_PORT))
        xxlJobExecutor.setAppName(com.waykichain.chain.commons.biz.env.Environment.TASK_APP_NAME)
        xxlJobExecutor.setAdminAddresses(com.waykichain.chain.commons.biz.env.Environment.TASK_JOB_ADMIN_URL)
        xxlJobExecutor.setLogPath(com.waykichain.chain.commons.biz.env.Environment.TASK_LOG_PATH)
        return xxlJobExecutor
    }

}