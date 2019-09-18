package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 17:37
 *
 * @Description:    $des
 *
 */
class ContractRegidVO: Serializable {

    @ApiModelProperty("智能合约的regid")
    var regid: String? = null

    @ApiModelProperty("智能合约的regid")
    var regidhex: String? = null
}