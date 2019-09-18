package com.waykichain.chain.tx

import java.math.BigDecimal

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/8/22 19:47
 *
 * @Description:    收据
 *
 */
class CdpTxReceipt {


    var tx_type: String? = null

    var coin_symbol: String? = null

    var coin_amount: BigDecimal = BigDecimal.ZERO

    var from_uid: String? = null

    var to_uid: String? = null
}