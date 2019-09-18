package com.waykichain.coin.wicc.vo.tx

/**
 *  Created by yehuan on 2019/7/11
 */

class DexSettleOrderTxDetailVO: BaseTxDetailVO(){

    lateinit var dealitems: ArrayList<DealItem>

     class DealItem{

        lateinit var buyorderid: String
        lateinit var sellorderid: String
        var coinamount = 1L
        var assetamount = 1L
        var price = 1L

    }
}