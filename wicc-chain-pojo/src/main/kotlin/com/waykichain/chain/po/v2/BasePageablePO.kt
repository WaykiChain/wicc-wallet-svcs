package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Min

open class BasePageablePO {

    @ApiModelProperty(value = "当前页，从1开始（默认1）", example = "1")
    var currentpage: Int = 1

    @ApiModelProperty(value="每页条数（默认20）", example = "20")
    @Min(value=1,message = "[in param error] Minimum value of pageSize is 1")
    var pagesize: Int = 20

}