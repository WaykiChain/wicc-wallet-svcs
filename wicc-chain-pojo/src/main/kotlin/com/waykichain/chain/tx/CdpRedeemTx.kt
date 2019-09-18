package com.waykichain.coin.wicc.vo.tx

import java.math.BigDecimal

/**
 *  Created by yehuan on 2019/7/11
 */

class CdpRedeemTx: BaseTx(){

    var cdp_txid: String? = null

    var scoins_to_repay: Long = 0L

    var assets_to_redeem =  mutableMapOf<String, BigDecimal>()

}