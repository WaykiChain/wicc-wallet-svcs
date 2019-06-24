package com.waykichain.chain.dict

enum class ContractAdminType(val code:Int, val msg:String) {

    /**
     * 100:喂价admin | 200：竞猜admin | 210：彩票admin
     * 1000开头为用户账户金额真实增加，
     */
    EXCHANGE_RATE(100, "喂价admin"),
    BET_ADMIN(200, "竞猜admin"),
    LOTO_ADMIN(210, "彩票admin")

}