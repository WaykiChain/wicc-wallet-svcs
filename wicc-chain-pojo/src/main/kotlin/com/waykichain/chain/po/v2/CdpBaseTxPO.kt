package com.waykichain.chain.po.v2

import com.waykichain.chain.dict.SysCoinType
import io.swagger.annotations.Api
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/8/17 16:41
 *
 * @Description:    $des
 *
 */
open class CdpBaseTxPO {

    @ApiModelProperty("用户地址")
    val address: String? = null

    @ApiModelProperty("小费数量(sawi)", example = "100000")
    val fee: BigDecimal? = null

    @ApiModelProperty("小费币种", example = "WICC")
    val feesymbol: String? = SysCoinType.WICC.code

}