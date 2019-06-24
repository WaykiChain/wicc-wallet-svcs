package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 16:33
 *
 * @Description:    创建调用智能合约交易
 *
 */
class CallContracttxVO: Serializable {

    @ApiModelProperty("调用合约的交易哈希")
    var hash: String? = null

}