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
 * @CreateDate:     2019/4/13 10:09
 *
 * @Description:    创建离线激活账户的交易内容
 *
 */
class GenRegisterAccountrawPO: Serializable {

    @ApiModelProperty("激活使用的小费，单位为sawi")
    @NotNull(message = "[in param error] fee is null")
    var fee: BigDecimal? = null

//    @ApiModelProperty("激活时的区块高度", hidden = true)
//    @Min(value = 0, message = "[in param error] height")
//    @NotNull(message = "[in param error] height is null")
//    var height: Long? = null

    @ApiModelProperty("激活账户的公钥")
    @NotBlank(message = "[in param error] publickey is null")
    var publickey: String? = null
}