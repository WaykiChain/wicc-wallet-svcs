package com.waykichain.chain.vo

import java.io.Serializable
import java.math.BigDecimal

class BalanceVO : Serializable {
    var balance:BigDecimal? = null
    var number:Int? =null
    var regId:String ?=null

}