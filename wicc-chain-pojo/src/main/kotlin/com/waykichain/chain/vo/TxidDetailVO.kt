package com.waykichain.chain.vo

import java.io.Serializable
import java.math.BigDecimal

class TxidDetailVO : Serializable {
    var txid:String? = null

    var txType:String ?= null
    var regId:String ?=null
    var sendAddress:String ?= null
    var toAddress:String ?= null
    var amount:BigDecimal ?= null
    var fee:BigDecimal ?= null
    var contract:String ?= null
    var memo:String ?= null
    var arguments:String ?= null


}