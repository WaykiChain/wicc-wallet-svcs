package com.waykichain.coin.wicc.vo.tx

/**
 *  Created by yehuan on 2019/7/24
 */

class UCoinBlockRewardTx: BaseTx(){

    var reward_fees: RewardValue? = null

    val inflated_bcoins: Long = 0L

}

class RewardValue {

    var WICC = 0L

    var WUSD = 0L
}