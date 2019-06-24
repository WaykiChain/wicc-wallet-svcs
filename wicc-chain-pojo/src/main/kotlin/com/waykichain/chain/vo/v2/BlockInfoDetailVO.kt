package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 15:20
 *
 * @Description:    区块信息
 *
 */
class BlockInfoDetailVO: Serializable {

    @ApiModelProperty("节点版本号")
    var version: Long? = null

    @ApiModelProperty("节点的编译版本号")
    var fullversion: String? = null

    @ApiModelProperty("协议版本号")
    var protocolversion: Long? = null

    @ApiModelProperty("钱包版本号")
    var walletversion: Long? = null

    @ApiModelProperty("钱包中的总余额")
    var balance: BigDecimal? = null

    @ApiModelProperty("分叉时间")
    var timeoffset: Long? = null

    @ApiModelProperty("节点代理地址:端口（host:port ）")
    var proxy: String? = null

    @ApiModelProperty("网络类型")
    var nettype: String? = null

    @ApiModelProperty("链的工作量")
    var chainwork: String? = null

    @ApiModelProperty("最新区块产生时间")
    var tipblocktime: Long? = null

    @ApiModelProperty("x.xxxx 交易费fee")
    var paytxfee: BigDecimal? = null

    @ApiModelProperty("x.xxxx 交易费fee的最小值")
    var relayfee: BigDecimal? = null

    @ApiModelProperty("")
    var fuelrate: Int? = null

    @ApiModelProperty("")
    var fuel: Int? = null

    @ApiModelProperty("节点数据保存路径")
    var datadirectory: String? = null

    @ApiModelProperty("最新区块hash值")
    var tipblockhash: String? = null

    @ApiModelProperty("网络中最新区块高度")
    var syncheight: Long? = null

    @ApiModelProperty("当前节点中最新区块高度")
    var blocks: Long? = null

    @ApiModelProperty("当前连接数")
    var connections: Int? = null

    @ApiModelProperty("错误信息")
    var errors: String? = null


}