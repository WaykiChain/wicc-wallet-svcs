package com.waykichain.coin.wicc.vo.tx

/**
 *  Created by yehuan on 2019/7/11
 */

class DexSettleOrderTx: BaseTx(){

    lateinit var deal_items: ArrayList<DealItem>

    public class DealItem{

        lateinit var buy_order_id: String
        lateinit var sell_order_id: String
        var coin_amount = 0L
        var asset_amount = 0L
        var price = 0L

    }
}