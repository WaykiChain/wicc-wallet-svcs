package com.waykichain.chain.commons.biz.dict

/**
 * Created by Joss on 2018/9/11.
 */
enum class WiccTransacationType(val type: String, val msg: String) {
    COMMON_TX             ("COMMON_TX",  "交易方式:普通交易"),
    CONTRACT_TX           ("CONTRACT_TX",  "交易方式:合约"),
    REG_ACCT_TX           ("REG_ACCT_TX",  "交易方式:激活"),
    REG_APP_TX            ("REG_APP_TX",  "发布合约"),
    REWARD_TX             ("REWARD_TX",  "挖矿奖励"),
    DELEGATE_TX           ("DELEGATE_TX", "投票")
}