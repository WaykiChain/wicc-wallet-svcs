package com.waykichain.chain.commons.biz.dict

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/25 10:41
 *
 * @Description:    链回滚-区块备份信息-状态
 *
 */
enum class RollbackBlockBakTypeDict(val code: Int, val message: String) {

    UN_PROCESSED(100, "未处理"),
    PROCESSED(200, "已处理")
}