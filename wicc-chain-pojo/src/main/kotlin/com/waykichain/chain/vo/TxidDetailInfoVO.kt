package com.waykichain.chain.vo

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.math.BigDecimal

class TxidDetailInfoVO : Serializable {
    /**
     * 交易hash
     */
    var hash: String? = null
    /**
     * REG_ACCT_TX：用户激活交易
     * COMMON_TX：WICC转账交易
     * REG_APP_TX：合约发布
     * CONTRACT_TX：合约调用交易
     * REWARD_TX：矿工奖励
     * DELEGATE_TX: 投票
     */
    var txtype: String? = null
    /**
     * 区块版本号
     */
    var ver: String? = null
    /**
     * 发送方地址的激活id
     */
    var regid: String? = null
    /**
     * 发送方地址
     */
    var addr: String? = null
    /**
     * 接收方地址的激活id
     */
    var desregid: String? = null
    /**
     * 接收方地址
     */
    var desaddr: String? = null
    /**
     * 交易转账wicc
     */
    var money: BigDecimal? = null
    /**
     * 交易中消耗的小费
     */
    var fees: BigDecimal? = null
    /**
     * 发送交易时的高度
     */
    var height: Int? = null

    /**
     * 合约内容，仅当该交易未合约交易时此才会有值
     */
    @JsonProperty("contract")
    var contract: String? = null
    /**
     * 区块hash
     */
    var blockhash: String? = null
    /**
     * 该交易被矿工确认时的区块高度
     */
    var confirmHeight: Int? = null
    /**
     * 该交易被矿工确认时间的时间戳
     */
    var confirmedtime: Long? = null
    /**
     * 交易签名字段
     */
    var rawtx: String? = null


}