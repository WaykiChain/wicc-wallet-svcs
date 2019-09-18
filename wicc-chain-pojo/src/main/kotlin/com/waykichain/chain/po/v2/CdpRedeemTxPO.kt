package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/8/17 16:35
 *
 * @Description:    $des
 *
 */
class CdpRedeemTxPO: CdpBaseTxPO()  {

   @ApiModelProperty("链上唯一标识")
   val cdpid: String? = null

   @ApiModelProperty("偿还数量(sawi)")
   val repayamount: BigDecimal? = null

   @ApiModelProperty("赎回数量(sawi)")
   val redeemamount: BigDecimal? = null
}