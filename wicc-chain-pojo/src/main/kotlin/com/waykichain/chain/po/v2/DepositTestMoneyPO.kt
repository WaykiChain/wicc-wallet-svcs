package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 20:37
 *
 * @Description:    添加测试金额
 *
 */
class DepositTestMoneyPO: Serializable {

    @ApiModelProperty("接收方地址")
    @NotBlank(message = "[in param error] recviver is null")
    var recviver: String? = null
}