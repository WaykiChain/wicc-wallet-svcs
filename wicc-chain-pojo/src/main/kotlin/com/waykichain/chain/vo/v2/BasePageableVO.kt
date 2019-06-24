package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty


open class BasePageableVO {

    @ApiModelProperty("当前页")
    var currentpage: Int? = null

    @ApiModelProperty("总页数")
    var totalpages: Int? = null

    @ApiModelProperty("总条数")
    var totalcount: Int? = null

}