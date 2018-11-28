package com.waykichain.chain.commons.biz.env


/**
 * Created by Joss on 2017/9/11.
 */
object Environment : BaseEnv() {

    @JvmField
    var WICC_SERVICE_ENVIRONMENT = env("WICC_SERVICE_ENVIRONMENT", "dev")

    @JvmField
    val WICC_LOG_LEVEL = env("WICC_LOG_LEVEL", "TRACE")
    /**
     * mysql config
     */
    @JvmField
    val MYSQL_URL = env("MYSQL_URL", "jdbc:mysql://10.0.0.4:3306/wicc-chain-testnet?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false")
    @JvmField
    val MYSQL_DRIVER = env("MYSQL_DRIVER", "com.mysql.jdbc.Driver")
    @JvmField
    var MYSQL_USERNAME = env("MYSQL_USERNAME", "wicc-rw")
    @JvmField
    var MYSQL_PASSWORD = env("MYSQL_PASSWORD", "wicc@123456")

    @JvmField
    val MYSQL_INITIALSIZE = env("MYSQL_INITIALSIZE", 10)
    @JvmField
    val MYSQL_MIN_IDLE = env("MYSQL_MIN_IDLE", 5)
    @JvmField
    val MYSQL_MAX_ACTIVE = env("MYSQL_MAX_ACTIVE", 20)

    /**
     * 定时任务
     */
    @JvmField
    var TASK_JOB_ADMIN_URL = env("TASK_JOB_ADMIN_URL", "http://10.0.0.4:8888")
    @JvmField
    var TASK_APP_NAME = env("TASK_APP_NAME", "chain-task-excutor")
    @JvmField
    var TASK_EXECUTOR_IP = env("TASK_EXECUTOR_IP", "10.0.0.4")
    @JvmField
    var TASK_EXECUTOR_PORT = env("TASK_EXECUTOR_PORT", "9999") //RPC Port
    @JvmField
    var TASK_LOG_PATH = env("TASK_LOG_PATH", "/tmp/wicc/logs")


    /**
     * 监控设置
     */

    @JvmField
    var MINI_TRANSER_AMOUNT = env("MINI_TRANSER_AMOUNT", 50000000)

}
