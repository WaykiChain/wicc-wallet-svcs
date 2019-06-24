package com.waykichain.chainstarter.config

import com.waykichain.JsonRpcClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *  Created by yehuan on 2019/6/4
 */

@Configuration
open class WaykiChainAutoConfiguration{

    @Bean("client")
    open fun client(): JsonRpcClient {
        return JsonRpcClient(
                Environment.WICC_HOST_IP,
                Environment.WICC_HOST_PORT,
                Environment.WICC_RPC_USERNAME,
                Environment.WICC_RPC_PASSWORD,
                false)
    }

}