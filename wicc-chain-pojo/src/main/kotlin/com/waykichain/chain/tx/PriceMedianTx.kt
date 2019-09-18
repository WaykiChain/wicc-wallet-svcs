package com.waykichain.coin.wicc.vo.tx

/**
 *  Created by yehuan on 2019/7/19

 */

class PriceMedianTx: BaseTx(){

    lateinit var median_price_points: ArrayList<PriceItem>


    public class PriceItem{

        lateinit var coin_symbol: String

        lateinit var price_symbol: String

        var price: Long = 0
    }

}