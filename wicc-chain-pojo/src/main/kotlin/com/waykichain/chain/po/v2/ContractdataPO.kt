package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable
import java.util.stream.Collectors

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 17:20
 *
 * @Description:    获取智能合约相关原生数据信息
 *
 */
class ContractdataPO: Serializable {

    @ApiModelProperty("合约regid")
    @NotBlank(message = "[in param error] regid is null")
    var regid:String? = null

    @ApiModelProperty("智能合约数据的key值")
    @NotBlank(message = "[in param error] key is null")
    var key:String ?= null

    @ApiModelProperty(value = "返回数据类型（STRING/NUMBER/HEX）", example = "STRING")
    var returndatatype:String ?= null
}