package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotNull

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/11 21:14
 *
 * @Description:    $des
 *
 */
class BlockHashVO {

    @ApiModelProperty("区块哈希")
    var hash: String? = null
}