package com.waykichain.chain.commons.biz.dict

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/24 17:18
 *
 * @Description:    扫链服务器提示信息类型
 *
 */
enum class BcWiccAlertLogType(val type: String, val num: Int, val msg: String) {
    CHAIN_ROOLBACK("chain_rollback", 100, "链回滚")
}