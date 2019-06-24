package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/13 15:23
 *
 * @Description:    $des
 *
 */
class GenSendToAddressTxrawVO: Serializable {

    @ApiModelProperty("创建交易签名产生的签名字段")
    var rawtx: String? = null
}