package com.waykichain.chain.commons.biz.dict

/**
 * Created by Joss on 2018/9/11.
 */
enum class NotifyMessageIdDict(val code: String, val msg: String) {
    TRANSACTION_NOTIFY            ("10", "扫链服务的通知机器人-转账通知"),
    MONEY_CHANGE_NOTIFY     ("20", "扫链服务的通知机器人-大额转账通知"),
    ROLLBACK_NOTIFY ("30","区块链回滚通知机器人-回滚通知")
}