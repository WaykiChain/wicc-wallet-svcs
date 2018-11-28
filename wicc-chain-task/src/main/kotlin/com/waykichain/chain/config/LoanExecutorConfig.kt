package com.waykichain.chain.config

import com.xxl.job.core.executor.XxlJobExecutor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by Joss on 8/22/17.
 */

@Configuration
@ComponentScan("com.waykichain")
open class LoanExecutorConfig : com.waykichain.chain.commons.biz.configuration.TaskExecutorConfig(){

    @Bean(initMethod = "start", destroyMethod = "destroy")
    open fun xxlJobExecutor(): XxlJobExecutor {
        return init()
    }

}