package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal

class RegisterAccountVO : Serializable {

    @ApiModelProperty("交易哈希")
    var hash: String? = null

}