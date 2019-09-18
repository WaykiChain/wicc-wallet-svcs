package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/8/17 14:44
 *
 * @Description:    cdp详情
 *
 */
class CdpInfoVO {
    var cdp: CdpInfoDetailVO? = null
}

class CdpInfoDetailVO {

    @ApiModelProperty("cdp链上唯一标识")
    val cdpid: String? = null
    @ApiModelProperty("用户regid")
    val regid: String? = null
    @ApiModelProperty("最后同步高度")
    val lastheight: Long? = null
    @ApiModelProperty("抵押币种")
    val bcoinsymbol = "WICC"
    @ApiModelProperty("抵押总量")
    val totalbcoin: Long? = null
    @ApiModelProperty("贷出币种")
    val scoinsymbol = "WUSD"
    @ApiModelProperty("贷出总量")
    val totalscoin: Long? = null
    @ApiModelProperty("抵押率")
    val collateralratio: String? = null
}