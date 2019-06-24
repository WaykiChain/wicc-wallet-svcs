package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/12 10:39
 *
 * @Description:    区块信息
 *
 */
class BlockInfoPO: Serializable {

    @ApiModelProperty("区块高度/区块哈希")
    @NotBlank(message = "[in param error] height is null")
    var heightorhash: String? = null

}