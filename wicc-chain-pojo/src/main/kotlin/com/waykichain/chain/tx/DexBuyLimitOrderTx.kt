package com.waykichain.coin.wicc.vo.tx

/**
 *  Created by yehuan on 2019/7/11
 */

class DexBuyLimitOrderTx: BaseTx(){

    var signature: String? = null

    var asset_symbol: String? = null

    var asset_amount: Long = 0L

    var price: Long = 0L

}