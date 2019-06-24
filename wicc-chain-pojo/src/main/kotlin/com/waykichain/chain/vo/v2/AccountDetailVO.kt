package com.waykichain.chain.vo.v2


import com.waykichain.chain.po.v2.BasePO
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

    @ApiModelProperty("地址的余额,单位为sawi")
    var balance: BigDecimal? = null

    @ApiModelProperty("投票数量（涉及Dpos投票的参数）")
    var votes: Long? = null

    @ApiModelProperty("(可忽略)")
    var updateheight: Int? = null

    @ApiModelProperty("所在位置; inwallet:没有收到过币，inblock:收到过币")
    var position: String?=null
//    @ApiModelProperty("投票列表")
//    var voteFundList: MutableList<VoteFund>?=null
}

//class VoteFund: Serializable{
//    @ApiModelProperty("地址")
//    var address: String? = null
//    @ApiModelProperty("票数")
//    var value: Long? = null
//}