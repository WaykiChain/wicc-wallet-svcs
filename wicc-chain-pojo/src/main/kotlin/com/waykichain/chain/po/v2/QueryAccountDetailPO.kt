package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable

class QueryAccountDetailPO : Serializable, BasePO() {

    @ApiModelProperty("address/regid")
    @NotBlank(message = "[in param error] address is null")
    var address:String ?= null
}