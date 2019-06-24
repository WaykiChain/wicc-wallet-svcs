package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/11 21:21
 *
 * @Description:    区块链相关信息
 *
 */
class BlockChainInfoVO: Serializable {

    @ApiModelProperty("main:主网、testnet:测试网、regtest:私有链")
    var chain: String? = null

    @ApiModelProperty("当前节点最新高度")
    var blocks: Long? = null

    @ApiModelProperty("当前节点最新区块哈希")
    var bestblockhash: String? = null

    @ApiModelProperty("验证进度")
    var verificationprogress: BigDecimal? = null

    @ApiModelProperty("链的工作量")
    var chainwork: String? = null
}