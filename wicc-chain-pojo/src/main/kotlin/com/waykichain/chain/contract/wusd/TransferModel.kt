package com.waykichain.chain.contract.wusd

open class TransferModel {

    //<发送地址
    var fromAddress: String = ""
    
    var toAddress: String = ""
    var amount: Long = 0
    var fee: Long = 0

}