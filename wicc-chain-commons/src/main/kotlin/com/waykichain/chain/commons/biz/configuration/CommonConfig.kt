package com.waykichain.chain.commons.biz.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
open class CommonConfig {

    @Bean(initMethod = "init")
    open fun sysConfigLoadManager(): com.waykichain.chain.commons.biz.configuration.SysConfigLoadManager {
        return com.waykichain.chain.commons.biz.configuration.SysConfigLoadManager()
    }

}