package com.waykichain.chain.vo

import com.waykichain.chain.vo.v2.AccountTokenInfo
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal

class BalanceVO : Serializable {
    var balance:BigDecimal? = null
    var balancewusd:BigDecimal? = null
    var number:Int? =null
    var regId:String ?=null
    @ApiModelProperty("余额列表")
    val tokens = mutableMapOf<String, AccountTokenInfo>()
}