package com.waykichain.chain.commons.biz.env.coin

import com.waykichain.chain.commons.biz.env.BaseEnv

/**
 * Created by Joss on 2018/9/11.
 */
object Environment : com.waykichain.chain.commons.biz.env.BaseEnv(){

    /**
     * config of wicc
     */
    @JvmField
    var WICC_HOST_IP = env("WICC_HOST_IP", "10.0.0.4")
    @JvmField
    var WICC_HOST_PORT = env("WICC_HOST_PORT", 6967)
    @JvmField
    var WICC_RPC_USERNAME = env("WICC_RPC_USERNAME", "waykichain")
    @JvmField
    var WICC_RPC_PASSWORD = env("WICC_RPC_PASSWORD", "admin123")

}