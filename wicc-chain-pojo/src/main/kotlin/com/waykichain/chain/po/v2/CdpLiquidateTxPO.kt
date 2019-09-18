package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/8/17 16:35
 *
 * @Description:    $des
 *
 */
class CdpLiquidateTxPO: CdpBaseTxPO()  {

   @ApiModelProperty("链上唯一标识")
   val cdpid: String? = null

   @ApiModelProperty("清算数量(sawi)")
   val liquidateamount: String? = null
}