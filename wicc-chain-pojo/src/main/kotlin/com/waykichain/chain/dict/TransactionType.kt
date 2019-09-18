package com.waykichain.chain.dict

import com.waykichain.chain.tx.UcoinStakeTx
import com.waykichain.coin.wicc.vo.tx.*

enum class TransactionType(val code: Int, val type: String, val description: String, val txClazz: Class<*>?) {

    NULL_TX(0, "NULL_TX", "", BaseTx :: class.java),
    BLOCK_REWARD_TX(1, "BLOCK_REWARD_TX", "挖矿奖励", BlockRewardTx::class.java),
    ACCOUNT_REGISTER_TX(2, "ACCOUNT_REGISTER_TX", "账户激活", AccountRegistTx::class.java),
    BCOIN_TRANSFER_TX(3, "BCOIN_TRANSFER_TX", "普通交易", BCoinTransferTx::class.java),
    LCONTRACT_DEPLOY_TX(4, "LCONTRACT_DEPLOY_TX", "调用合约", ContractDeployTx::class.java),
    LCONTRACT_INVOKE_TX(5, "LCONTRACT_INVOKE_TX", "发布合约", ContractInvokeTx::class.java),
    DELEGATE_VOTE_TX(6, "DELEGATE_VOTE_TX", "投票", DelegateVoteTx::class.java),
    BCOIN_TRANSFER_MTX(7, "BCOIN_TRANSFER_MTX", "", null),
    UCOIN_STAKE_TX(8, "UCOIN_STAKE_TX", "", UcoinStakeTx::class.java),

    ASSET_ISSUE_TX(10, "ASSET_ISSUE_TX", "", null),
    ASSET_UPDATE_TX(11, "", "", null),
    UCOIN_TRANSFER_TX(12, "UCOIN_TRANSFER_TX", "", UCoinTransferTx::class.java),
    UCOIN_REWARD_TX(13, "UCOIN_REWARD_TX", "", UCoinRewardTx::class.java),
    UCOIN_BLOCK_REWARD_TX(14, "UCOIN_BLOCK_REWARD_TX", "", UCoinBlockRewardTx::class.java),
    UCONTRACT_DEPLOY_TX(15, "UCONTRACT_DEPLOY_TX", "", null),
    UCONTRACT_INVOKE_TX(16, "UCONTRACT_INVOKE_TX", "", null),
    PRICE_FEED_TX(17, "PRICE_FEED_TX", "", PriceFeedTx::class.java),
    PRICE_MEDIAN_TX(18, "PRICE_MEDIAN_TX", "", PriceMedianTx::class.java),
    SYS_PARAM_PROPOSE_TX(19, "SYS_PARAM_PROPOSE_TX", "", null),
    SYS_PARAM_RESPONSE_TX(20, "SYS_PARAM_RESPONSE_TX", "", null),

    CDP_STAKE_TX(61, "CDP_STAKE_TX", "", CdpStakeTx::class.java),
    CDP_REDEEM_TX(62, "CDP_REDEEM_TX", "", CdpRedeemTx::class.java),
    CDP_LIQUIDATE_TX(63, "CDP_LIQUIDATE_TX", "", CdpLiquidateTx::class.java),

    DEX_TRADEPAIR_PROPOSE_TX(81, "DEX_TRADEPAIR_PROPOSE_TX", "", null),
    DEX_TRADEPAIR_LIST_TX(82, "DEX_TRADEPAIR_LIST_TX", "", null),
    DEX_TRADEPAIR_DELIST_TX(83, "DEX_TRADEPAIR_DELIST_TX", "", null),
    DEX_LIMIT_BUY_ORDER_TX(84, "DEX_LIMIT_BUY_ORDER_TX", "", DexBuyLimitOrderTx::class.java),
    DEX_LIMIT_SELL_ORDER_TX(85, "DEX_LIMIT_SELL_ORDER_TX", "", DexSellLimitOrderTx::class.java),
    DEX_MARKET_BUY_ORDER_TX(86, "DEX_MARKET_BUY_ORDER_TX", "", DexBuyMarketOrderTx::class.java),
    DEX_MARKET_SELL_ORDER_TX(87, "DEX_MARKET_SELL_ORDER_TX", "", DexSellMarketOrderTx::class.java),
    DEX_CANCEL_ORDER_TX(88, "DEX_CANCEL_ORDER_TX", "", DexCancelOrderTx::class.java),
    DEX_TRADE_SETTLE_TX(89, "DEX_TRADE_SETTLE_TX", "", DexSettleOrderTx::class.java);

    companion object {
        private val map = TransactionType.values().associateBy(TransactionType::name)
        fun parseTransactionTypeToCode(type: String): Int? {
            val t = map[type]?:return null
            return t.code
        }

        fun <T: BaseTx> parseTransactionTypeToTxClass(type: String): Class<T> {
            val t = map[type]?: NULL_TX
            return t.txClazz as Class<T>
        }

        fun getCodeByName(name: String): Int {
            return (map[name]?:NULL_TX).code
        }
    }

}
