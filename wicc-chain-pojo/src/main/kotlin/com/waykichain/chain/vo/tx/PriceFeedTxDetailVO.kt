package com.waykichain.coin.wicc.vo.tx

/**
 *  Created by yehuan on 2019/7/24
 */

class PriceFeedTxDetailVO: BaseTxDetailVO(){

    lateinit var pricepoints: ArrayList<PriceItem>


    class PriceItem{

        lateinit var coinsymbol: String

        lateinit var pricesymbol: String

        var price: Long = 0
    }

}