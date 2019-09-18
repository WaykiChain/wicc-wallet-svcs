package com.waykichain.chainstarter.config


/**
 * Created by Joss on 2018/9/11.
 */
object Environment : BaseEnv(){

    /**
     * config of wicc
     */
    @JvmField
    var WICC_HOST_IP = env("WICC_HOST_IP", "10.0.0.31")
    @JvmField
    var WICC_HOST_PORT = env("WICC_HOST_PORT", 6968)
    @JvmField
    var WICC_RPC_USERNAME = env("WICC_RPC_USERNAME", "wayki")
    @JvmField
    var WICC_RPC_PASSWORD = env("WICC_RPC_PASSWORD", "admin@123")

}