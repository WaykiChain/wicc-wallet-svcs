package com.waykichain.coin.wicc.vo.tx

import java.math.BigDecimal

/**
 *  Created by yehuan on 2019/7/11
 */

class CdpStakeTx: BaseTx(){

    var cdp_txid: String? = null

    var assets_to_stake = mutableMapOf<String, BigDecimal>()

    var scoins_to_mint:Long = 0L

    var scoin_symbol: String? = null
}