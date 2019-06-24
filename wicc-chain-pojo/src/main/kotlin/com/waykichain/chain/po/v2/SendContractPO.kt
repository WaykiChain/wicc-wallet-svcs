package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable
import javax.validation.constraints.NotNull

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 16:52
 *
 * @Description:    管理员发起合约交易
 *
 */
class SendContractPO: Serializable {

    @ApiModelProperty("请求的uuid")
    @NotBlank(message = "[in param error] requestUUID is null")
    var requestUUID:String ? = null

    @ApiModelProperty("合约参数")
    @NotBlank(message = "[in param error] parameter is null")
    var parameter: String ? = null

    @ApiModelProperty("后台账户(100:喂价admin | 200：竞猜admin | 210：彩票admin')")
    @NotNull(message = "[in param error] contractAdminType is null")
    var contractAdminType:Int ? = null

    @ApiModelProperty("智能合约regid")
    @NotBlank(message = "[in param error] regid is null")
    var regid: String ?= null

    @ApiModelProperty("交易金额")
    var amount: Long ?= 0L

    @ApiModelProperty("小费（默认100000）")
    var fee: Long?= null
}