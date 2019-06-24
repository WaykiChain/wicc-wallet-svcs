package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/11 21:11
 *
 * @Description:    根据区块高度获取区块hash
 *
 */
class BlockHashPO: Serializable {

    @ApiModelProperty("区块高度 0 代表创世区块")
    @NotNull(message = "[in param error] index is null")
    @Min(value = 0, message = "[in param error] index >= 0")
    val height: Int? = null
}