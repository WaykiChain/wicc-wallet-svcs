package com.waykichain.coin.wicc.vo.tx

import com.waykichain.chain.tx.CdpTxReceipt

/**
 *  Created by yehuan on 2019/7/11
 */

open class BaseTx{


    var block_hash: String = ""

    var rawtx: String? = null

    var txid: String = ""

    var tx_type: String = ""

    var tx_uid: String = ""

    var from_addr: String = ""

    var to_uid: String? = null

    var to_addr: String? = null

    var fees: Long  = 0L

    var coin_amount = 0L

    var coin_symbol: String = "WICC"

    var fee_symbol: String = "WICC"

    //确认高度
    var confirmed_height: Int = 0

    //确认时间(second)
    var confirmed_time: Long = 0

    //版本号
    var ver: Int = 1

    //提交高度
    var valid_height:Int = 0

    var arguments: String? = null

    var receipt = arrayListOf<CdpTxReceipt>()

}