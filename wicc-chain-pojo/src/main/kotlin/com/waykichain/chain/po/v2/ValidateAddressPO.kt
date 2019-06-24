package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/12 12:00
 *
 * @Description:    检查普通地址或者合约地址是否有效
 *
 */
class ValidateAddressPO {

    @ApiModelProperty("普通账户/合约账户的地址/regid")
    @NotBlank(message = "[in param error] address is null")
    var address: String? = null
}