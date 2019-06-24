package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 17:26
 *
 * @Description:    合约相关原生数据信息
 *
 */
class ContractDataDetailVO: Serializable {

    @ApiModelProperty("合约regid")
    var regid:String ?= null
    @ApiModelProperty("智能合约数据的key值")
    var key:String ?= null
    @ApiModelProperty("智能合约数据的value值")
    var value:Any ?= null

}