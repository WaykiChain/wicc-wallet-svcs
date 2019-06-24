package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 19:53
 *
 * @Description:    获取汇率
 *
 */
class ExchangeRatePO: Serializable {

    @ApiModelProperty("合约regid")
    @NotBlank(message="[in param error] regid is null")
    var regid: String? = null
}