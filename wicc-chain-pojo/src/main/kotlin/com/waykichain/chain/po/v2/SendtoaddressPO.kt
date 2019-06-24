package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable
import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 12:00
 *
 * @Description:    发送交易
 *
 */
open class SendtoaddressPO: Serializable {

//    @ApiModelProperty("请求UUID")
//    @NotBlank(message="[in param error] uuid is null")
//    var uuid:String ?= null

    @ApiModelProperty("发送方地址")
    @NotBlank(message="【[in param error] sender is null")
    var sender: String? = null

    @ApiModelProperty("接收方地址")
    @NotBlank(message="[in param error] recviver is null")
    var recviver:String ?= null

    @ApiModelProperty("发送金额")
    @NotNull(message="[in param error] amount is null")
    @Min(value = 10000 ,message = "[in param error] Minimum value of amount is 10000sawi")
    var amount: BigDecimal?= null

}