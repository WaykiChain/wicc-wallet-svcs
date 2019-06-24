package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/4 19:35
 *
 * @Description:    WICC数量
 *
 */

class TotalCoinVO:Serializable {

    @ApiModelProperty("所有的WICC数量")
    var totalCoin: BigDecimal? = null
}