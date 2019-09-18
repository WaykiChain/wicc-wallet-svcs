package com.waykichain.chain.vo

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

class TransactionVO: Serializable {
    var symbol:String ?= null
    var confirmedHeight:Int ?= null
    var srcAddress:String ?= null
    var desAddress: String? = null
    var tx:String ?= null
    @ApiModelProperty("交易币种")
    var coinsymbol: String? = null
    @ApiModelProperty("小费币种")
    var feesymbol: String? = null
    var amount:BigDecimal ?= null
    var confirmedTime: Date ?= null
    var fees: BigDecimal? = null
    var height: Int? = null
    var contract: String? = null
    var srcRegId: String? = null
    var desRegId: String? = null
    var txType: Int? = null



}