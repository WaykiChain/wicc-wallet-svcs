package com.waykichain.chain.dict

enum class WalletAddressType(val code:Int, val msg:String) {

    /**
     * 1000开头为用户账户金额真实增加，
     */
    CUSTOMER(100, "用户账号"),
    SYSTEM(800, "系统账号"),

}