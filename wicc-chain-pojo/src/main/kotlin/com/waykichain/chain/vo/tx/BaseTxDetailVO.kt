package com.waykichain.coin.wicc.vo.tx

import io.swagger.annotations.ApiModelProperty

open class BaseTxDetailVO{

    @ApiModelProperty("区块哈希")
    var blockhash: String? = null

    @ApiModelProperty("交易签名字段")
    var rawtx: String? = null

    @ApiModelProperty("交易哈希")
    lateinit var txid: String

    @ApiModelProperty("交易类型")
    lateinit var txtype: String

    @ApiModelProperty("发送方RegId")
    var txuid: String? = null

    @ApiModelProperty("发送方地址")
    var fromaddr: String = ""

    @ApiModelProperty("接收方RegId")
    var touid: String? = null

    @ApiModelProperty("接收方地址")
    var toaddr: String? = null

    @ApiModelProperty("小费")
    var fees: Long  = 0L

    @ApiModelProperty("交易金额")
    var coinamount = 0L

    @ApiModelProperty("交易币种")
    var coinsymbol: String = "WICC"

    @ApiModelProperty("小费币种")
    var feesymbol: String = "WICC"

    @ApiModelProperty("确认高度")
    var confirmedheight: Int = 0

    @ApiModelProperty("确认时间")
    var confirmedtime: Long = 0

    @ApiModelProperty("版本")
    var ver: Int = 1

    @ApiModelProperty("提交高度")
    var validheight:Int = 0

}