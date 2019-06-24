package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/12 10:06
 *
 * @Description:    区块信息
 *
 */
class BlockInfoVO: Serializable {

    @ApiModelProperty("区块哈希")
    var hash: String? = null

    @ApiModelProperty("区块确认数")
    var confirmations: Int? = null

    @ApiModelProperty("区块大小,单位是byte")
    var size: Int? = null

    @ApiModelProperty("区块高度")
    var height: Int? = null

    @ApiModelProperty("区块版本")
    var version: Int? = null

    @ApiModelProperty("merkle root值")
    var merkleroot: String? = null

    @ApiModelProperty("交易数")
    var txnumber: Int? = null

    @ApiModelProperty("交易哈希")
    var tx: List<String>? = null

    @ApiModelProperty("区块生成时间戳")
    var time: Long? = null

    @ApiModelProperty("随机数")
    var nonce: Long? = null

    @ApiModelProperty("父区块哈希")
    var previousblockhash: String? = null

    @ApiModelProperty("下一个区块哈希")
    var nextblockhash: String? = null

    @ApiModelProperty("燃料")
    var fuel: Int? = null

    @ApiModelProperty("")
    var fuelrate: Int? = null

    @ApiModelProperty("链的工作量")
    var chainwork: String? = null

}