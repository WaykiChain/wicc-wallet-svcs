package com.waykichain.chain.vo

import com.waykichain.chain.vo.v2.AccountTokenInfo
import com.waykichain.chain.vo.v2.VoteFund
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal

class AccountInfoVO : Serializable {
    var address: String? = null
    var keyID: String? = null
    var regID: String? = null
    var publicKey: String? = null
    var minerPKey: String? = null
    @ApiModelProperty("地址的WICC余额,单位为sawi")
    var balance: BigDecimal? = null
//    @ApiModelProperty("地址的WUSD余额,单位为sawi")
//    var balancewusd: BigDecimal? = null
    var votes: Long? = null
    var updateHeight: Int? = null
    var postion: String? = null
    var position: String?=null
    var nickid: String? = null

    @ApiModelProperty("投票列表")
    var votelist = arrayListOf<VoteFund>()
    @ApiModelProperty("余额列表")
    val tokens = mutableMapOf<String, AccountTokenInfo>()
    @ApiModelProperty("")
    var regidmature: Boolean? = null
}