package com.waykichain.coin.wicc.vo.tx

/**
 *  Created by yehuan on 2019/7/19

 */

class PriceMedianTxDetailVO: BaseTxDetailVO(){

    var medianpricepoints = arrayListOf<PriceItemVO>()


     class PriceItemVO{

        var coinsymbol: String? = null

        var pricesymbol: String? = null

        var price: Long = 0
    }

}