package com.waykichain.chain.commons.biz.xservice

import com.waykichain.JsonRpcClient
import com.waykichain.chain.commons.biz.env.coin.Environment
import com.waykichain.coin.wicc.WiccMethods
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * Created by Joss on 05/16/18.
 */

@Service("wiccMethodClient")
class WiccMethodClient {

    companion object {
        @JvmStatic
        val clients = ArrayList<JsonRpcClient>()
    }

    @Synchronized
    fun getClient(): WiccMethods {
        logger.info("btc client ip:${Environment.WICC_HOST_IP} port:${Environment.WICC_HOST_PORT}")
        if (clients.isEmpty()) {
            clients.add(client())
        }
        return WiccMethods(clients.first())
    }

    private fun client(): JsonRpcClient {
        return JsonRpcClient(
                Environment.WICC_HOST_IP,
                Environment.WICC_HOST_PORT,
                Environment.WICC_RPC_USERNAME,
                Environment.WICC_RPC_PASSWORD,
                false)
    }

    private val logger = LoggerFactory.getLogger(javaClass)
}