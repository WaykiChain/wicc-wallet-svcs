package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 17:33
 *
 * @Description:    获取智能合约的regid
 *
 */
class ContractRegidPO: Serializable {

    @ApiModelProperty("发布合约时的交易哈希")
    @NotBlank(message="[in param error] txhash is null")
    var txhash: String? = null
}