package com.waykichain.chain.vo

import java.io.Serializable
import java.math.BigDecimal
import java.util.*

class TransactionVO: Serializable {
    var symbol:String ?= null
    var number:Int ?= null
    var recvAddress:String ?= null
    var tx:String ?= null
    var amount:BigDecimal ?= null
    var transactionAt: Date ?= null

}