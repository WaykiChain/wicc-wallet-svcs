package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/13 10:14
 *
 * @Description:    创建离线激活账户的交易内容
 *
 */
class GenRegisterAccountrawVO: Serializable {

    @ApiModelProperty("交易哈希")
    var rawtx: String? = null
}