package com.waykichain.coin.wicc.vo.tx

/**
 *  Created by yehuan on 2019/7/11
 */

class DexSellLimitOrderTxDetailVO: BaseTxDetailVO(){

    lateinit var assetsymbol: String

    var assetamount: Long = 1L

    var price: Long = 1L

}