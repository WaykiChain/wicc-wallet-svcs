package com.waykichain.coin.wicc.vo.tx

/**
 *  Created by yehuan on 2019/7/24
 */

class UCoinBlockRewardTxDetailVO: BaseTxDetailVO(){

    var rewardfees: RewardValueVO? = null

    val inflatedbcoins: Long = 0L

}

class RewardValueVO {

    var wicc = 0L

    var wusd = 0L
}