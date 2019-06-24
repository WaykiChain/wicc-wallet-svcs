package com.waykichain.chain.po

import java.io.Serializable

class ContractTransactionPO : Serializable {
    var requestUUID:String ?= null

    var parameter: String ?= null
    var contractAdminType:Int ?=100
    var contractAddress: String ?= null
    var amount: Long ?= 0L
    var fee: Long?= 100000L
}