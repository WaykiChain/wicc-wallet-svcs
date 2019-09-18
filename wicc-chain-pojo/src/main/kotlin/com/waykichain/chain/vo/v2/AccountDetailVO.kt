package com.waykichain.chain.vo.v2


import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal

class AccountDetailVO : Serializable {

    @ApiModelProperty("普通账户/合约账户地址")
    var address: String? = null

    @ApiModelProperty("地址公钥两次sha之后得到的pubKeyHash")
    var keyid: String? = null

    @ApiModelProperty("地址对应的regid")
    var regid: String? = null

    @ApiModelProperty("地址对应的公钥，只有普通账户地址有")
    var publickey: String? = null

    @ApiModelProperty("矿工标识 (可忽略)")
    var minerpkey: String? = null

    @ApiModelProperty("地址的wicc余额,单位为sawi")
    var balance: Long? = null

    @ApiModelProperty("投票数量")
    var receivedvotes: Long? = null

    @ApiModelProperty("所在位置; inwallet:没有收到过币，inblock:收到过币")
    var position: String?=null

    @ApiModelProperty("投票列表")
    var votelist = arrayListOf<VoteFund>()

    @ApiModelProperty("余额列表")
    val tokens = mutableMapOf<String, AccountTokenInfo>()

    @ApiModelProperty("")
    var regidmature: Boolean? = null

    @ApiModelProperty("")
    var nickid: String? = null
}

class VoteFund {
    var voteType: String? = null
    var candidateUid: CandidateUid? = null
    var votedBcoins: Long? = null
}

class CandidateUid {

    var idType: String? = null
    var id: String? = null

}

class AccountTokenInfo {

    @ApiModelProperty("可用金额,单位为sawi")
    var freeAmount: Long? = null

    @ApiModelProperty("")
    var stakedAmount: Long? = null

    @ApiModelProperty("冻结金额,单位为sawi")
    var frozenAmount: Long? = null

}


