package com.waykichain.chainstarter.client

import com.waykichain.JsonRpcClient
import com.waykichain.coin.wicc.WiccMethods
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
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

        if (clients.isEmpty()) {
            clients.add(client)
        }
        return WiccMethods(clients.first())
    }


    @Autowired lateinit var client: JsonRpcClient

    private val logger = LoggerFactory.getLogger(javaClass)
}