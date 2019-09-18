package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/13 15:57
 *
 * @Description:    根据签名字段解析原始交易单
 *
 */
class DecodeRawtxVO: Serializable {


    @ApiModelProperty("交易hash")
    var hash: String? = null

    @ApiModelProperty("交易类型")
    var txtype: String? = null

    @ApiModelProperty("版本")
    var ver: Int? = null

    @ApiModelProperty("交易sender的regid")
    var regid: String? = null

    @ApiModelProperty("合约的regid")
    var appuid: String? = null

    @ApiModelProperty("交易sender的地址")
    var addr: String? = null

    @ApiModelProperty("交易receiver的regid")
    var desregid : String? = null

    @ApiModelProperty("交易receiver的地址")
    var desaddr  : String? = null

    @ApiModelProperty("交易金额 ，单位为 sawi")
    var money: BigDecimal? = null

    @ApiModelProperty("手续费，单位为 sawi")
    var fees: BigDecimal? = null

    @ApiModelProperty("交易发送签名时的高度")
    var height: Long? = null

    @ApiModelProperty("合约内容")
    var arguments: String? = null

    @ApiModelProperty("交易备注")
    var memo: String? = null
}