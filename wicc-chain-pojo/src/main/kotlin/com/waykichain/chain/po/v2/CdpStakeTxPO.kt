package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal
import javax.validation.constraints.NotNull

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/8/17 16:35
 *
 * @Description:    $des
 *
 */
class CdpStakeTxPO: CdpBaseTxPO() {

   @ApiModelProperty("抵押数量(sawi)")
   @NotNull(message = "[in param error] mintcombomoney is null")
   val stakecombomoney: BigDecimal? = null

   @ApiModelProperty("贷出数量(sawi)")
   @NotNull(message = "[in param error] mintcombomoney is null")
   val mintcombomoney: BigDecimal? = null

   @ApiModelProperty("cdp链上唯一标识")
   val cdpid: String? = null
}