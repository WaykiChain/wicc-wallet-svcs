package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 17:57
 *
 * @Description:    获取用户在智能合约中的相关信息
 *
 */
class ContractAccountInfoPO: Serializable {

    @ApiModelProperty("智能合约的regid")
    @NotBlank(message = "[in param error] regid is null")
    val contractregid: String? = null

    @ApiModelProperty("查询的用户地址/regid")
    @NotBlank(message = "[in param error] address is null")
    val address: String? = null
}