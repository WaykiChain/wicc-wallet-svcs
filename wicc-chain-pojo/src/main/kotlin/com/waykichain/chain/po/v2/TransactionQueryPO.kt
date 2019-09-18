package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable
import javax.validation.constraints.Max
import javax.validation.constraints.Min

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/4 20:21
 *
 * @Description:    交易记录查询
 *
 */
class TransactionQueryPO: Serializable,BasePageablePO() {


    @ApiModelProperty("交易地址(转出/转入)")
    @NotBlank(message = "【入参有误】交易地址不能为空")
    var address:String ?=null

    @ApiModelProperty(value = "交易方向 0-所有 1-转出 2-转入", example = "0")
    @Min(value=0,message = "【入参有误】交易方向")
    @Max(value=2,message = "【入参有误】交易方向")
    var trandirection: Int ?= null

    @ApiModelProperty(value = "起始高度", example = "1")
    @Min(value=1, message = "【入参有误】起始高度")
    var startheight:Int?= null

    @ApiModelProperty("交易币种", example = "WICC")
    var coinsymbol:String ?=null

    @ApiModelProperty(value = "交易类型 (不传/空:所有交易, BLOCK_REWARD_TX：矿工奖励  ACCOUNT_REGISTER_TX：账户激活 BCOIN_TRANSFER_TX：WICC交易 LCONTRACT_DEPLOY_TX：合约发布  LCONTRACT_INVOKE_TX：合约调用  DELEGATE_VOTE_TX：投票交易   BCOIN_TRANSFER_MTX   FCOIN_STAKE_TX：WGRT抵押   ASSET_ISSUE_TX  ASSET_UPDATE_TX UCOIN_TRANSFER_TX：多币种交易 UCOIN_REWARD_TX：系统初始化 UCOIN_BLOCK_REWARD_TX：多币种矿工奖励 UCONTRACT_DEPLOY_TX：多币种合约发布 UCONTRACT_INVOKE_TX: 多币种合约调用 PRICE_FEED_TX：喂价交易 PRICE_MEDIAN_TX：喂价中位数交易 SYS_PARAM_PROPOSE_TX SYS_PARAM_RESPONSE_TX\\r\\nCDP_STAKE_TX：CDP抵押 CDP_REDEEM_TX：CDP赎回  CDP_LIQUIDATE_TX：CDP清算 DEX_TRADEPAIR_PROPOSE_TX  DEX_TRADEPAIR_LIST_TX  DEX_TRADEPAIR_DELIST_TX DEX_LIMIT_BUY_ORDER_TX：限价买单 DEX_LIMIT_SELL_ORDER_TX：限价卖单 DEX_MARKET_BUY_ORDER_TX：市价买单 DEX_MARKET_SELL_ORDER_TX：市价卖单 DEX_CANCEL_ORDER_TX：取消挂单  DEX_TRADE_SETTLE_TX：撮合)")
    var txtype: String? = null
}