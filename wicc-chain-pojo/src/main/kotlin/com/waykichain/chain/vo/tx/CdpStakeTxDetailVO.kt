package com.waykichain.coin.wicc.vo.tx

import java.math.BigDecimal

/**
 *  Created by yehuan on 2019/7/11
 */

class CdpStakeTxDetailVO: BaseTxDetailVO(){

    lateinit var cdp_txid: String

    var assetstostake =  mutableMapOf<String, BigDecimal>()

    var scoinstomint:Long = 0L

    var scoinsymbol: String? = null

}