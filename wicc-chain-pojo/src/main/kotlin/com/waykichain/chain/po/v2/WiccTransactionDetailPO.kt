package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 11:05
 *
 * @Description:    根据交易哈希获取交易详情
 *
 */
class WiccTransactionDetailPO: Serializable {

    @ApiModelProperty("交易哈希")
    @NotBlank(message = "[in param error] hash is null")
    var hash:String?= null

}