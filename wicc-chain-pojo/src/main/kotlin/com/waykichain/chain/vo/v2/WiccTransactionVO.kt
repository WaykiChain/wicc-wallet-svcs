package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 10:01
 *
 * @Description:    交易详情
 *
 */
class WiccTransactionVO: Serializable {

    @ApiModelProperty("")
    var symbol:String ?= null
    @ApiModelProperty("发送方地址")
    var addr:String ?= null
    @ApiModelProperty("接收方地址")
    var desaddr: String? = null
    @ApiModelProperty("交易哈希")
    var hash:String ?= null
    @ApiModelProperty("区块哈希")
    var blockhash: String? = null
    @ApiModelProperty("交易签名字段")
    var rawtx: String? = null
    @ApiModelProperty("交易金额交易金额")
    var money: BigDecimal?= null
    @ApiModelProperty("交易确认时间")
    var confirmedtime: Date?= null
    @ApiModelProperty("该交易被矿工确认时的区块高度")
    var confirmedheight: Int? = null
    @ApiModelProperty("小费")
    var fees: BigDecimal? = null
    @ApiModelProperty("非确认高度")
    var height: Int? = null
    @ApiModelProperty("调用合约的参数")
    var arguments: String? = null
    @ApiModelProperty("发送方地址的激活id")
    var regid: String? = null
    @ApiModelProperty("接受方地址的激活id")
    var desregid: String? = null
    @ApiModelProperty("交易类型：REWARD_TX:系统分红，REG_ACCT_TX:钱包注册，COMMON_TX:普通转账，CONTRACT_TX合约交易，REG_APP_TX:合约发布，DELEGATE_TX:投票交易")
    var txtype: String? = null
    @ApiModelProperty("交易流向：1-转入 2-转出")
    var trandirection: Int? = null

}