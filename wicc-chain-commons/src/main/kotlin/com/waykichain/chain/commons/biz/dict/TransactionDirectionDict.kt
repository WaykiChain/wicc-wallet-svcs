package com.waykichain.chain.commons.biz.dict

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/4 20:38
 *
 * @Description:    资金流向
 *
 */
enum class TransactionDirectionDict(val numValue: Int, val value: String, val message: String) {
    TRAN_DIRECTION_IN(1, "in", "转入"),
    TRAN_DIRECTION_OUT(2, "out", "转出"),
    TRAN_DIRECTION_OTHER(3, "other", "其他")
}