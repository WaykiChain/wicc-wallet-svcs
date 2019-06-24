package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable
import java.math.BigDecimal
import javax.validation.constraints.Min

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 16:21
 *
 * @Description:    $des
 *
 */
class CallContracttxPO: Serializable {

    @ApiModelProperty("合约调用者地址/regid")
    @NotBlank(message = "[in param error] userregid is null")
    val calleraddress: String? = null

    @ApiModelProperty("智能合约的regid")
    @NotBlank(message = "[in param error] regid is null")
    val regid: String? = null

    @ApiModelProperty(" 向智能合约发送维基币的数量,单位为sawi")
    val amount: BigDecimal? = null

    @ApiModelProperty("调用合约的参数")
    val arguments: String? = null

    @ApiModelProperty("调用合约交易手续费, 最小 100000 sawi")
    @Min(value = 100000 ,message = "[in param error] Minimum value of feea is 100000sawi")
    val fee: BigDecimal? = null

}