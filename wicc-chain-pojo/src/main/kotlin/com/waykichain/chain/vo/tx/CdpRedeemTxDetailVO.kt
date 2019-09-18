package com.waykichain.coin.wicc.vo.tx

import java.math.BigDecimal

/**
 *  Created by yehuan on 2019/7/11
 */

class CdpRedeemTxDetailVO: BaseTxDetailVO(){

    lateinit var cdptxid: String

    var scoinstorepay: Long = 0L

    var assetstoredeem = mutableMapOf<String, BigDecimal>()

}