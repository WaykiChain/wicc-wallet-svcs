package com.waykichain.chain.dict

enum class AccountCheckBatchStatus(val code:Int, val msg:String) {

    /**
     * 1000开头为用户账户金额真实增加，
     */
    SUBMIT(100, "批次已生成"),
    READING_RUNNING(200, "批次准备生成"),
    CHECK_FINISHED(400, "批次检查完成"),
    FINISHED(800, ""),

}