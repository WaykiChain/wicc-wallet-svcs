package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/13 15:55
 *
 * @Description:    根据签名字段解析原始交易单
 *
 */
class DecodeRawtxPO {

    @ApiModelProperty("签名后的交易字段")
    @NotBlank(message = "[in param error] hexstring  is null")
    var rawtx: String? = null
}