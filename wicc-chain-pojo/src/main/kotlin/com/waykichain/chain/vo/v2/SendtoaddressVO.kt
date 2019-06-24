package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 13:53
 *
 * @Description:    转账返回
 *
 */
class SendtoaddressVO: Serializable {

    @ApiModelProperty("交易哈希")
    var hash: String? = null

}