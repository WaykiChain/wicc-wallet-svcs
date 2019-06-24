package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/15 10:36
 *
 * @Description:    获取智能合约信息
 *
 */
class ContractInfoPO : Serializable {

    @ApiModelProperty("智能合约regid")
    @NotBlank(message = "[in param error] regid is null")
    var regid: String? = null
}