package com.waykichain.coin.wicc.vo.tx

/**
 *  Created by yehuan on 2019/7/11
 */

class CdpLiquidateTx: BaseTx(){

    lateinit var cdp_txid: String

    var scoins_to_liquidate: Long = 0L

    var liquidate_asset_symbol: String? = null

}