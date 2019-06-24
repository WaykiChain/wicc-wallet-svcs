package com.waykichain.chain.vo.v2

import com.waykichain.chain.po.v2.BasePO
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/4 17:40
 *
 * @Description:    链账户余额
 *
 */
class AccountBalanceVO: Serializable {


    @ApiModelProperty("账户余额")
    var balance: BigDecimal? = null
    @ApiModelProperty("")
    var updateHeight:Int? =null
    @ApiModelProperty("地址对应的regid")
    var regId:String ?=null

}