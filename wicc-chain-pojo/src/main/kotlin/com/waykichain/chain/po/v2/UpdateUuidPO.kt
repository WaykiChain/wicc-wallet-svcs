package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable
import java.math.BigDecimal

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 12:00
 *
 * @Description:    更新UUID
 *
 */
class UpdateUuidPO: Serializable {

    @ApiModelProperty("请求UUID")
    @NotBlank(message = "[in param error] uuid is null")
    var requestUUID:String ?= null
}