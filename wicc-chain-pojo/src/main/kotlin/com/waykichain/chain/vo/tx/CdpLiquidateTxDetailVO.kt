package com.waykichain.coin.wicc.vo.tx

/**
 * CDP
 */
class CdpLiquidateTxDetailVO: BaseTx(){

    lateinit var cdp_txid: String

    var scoinstoliquidate: Long = 0L

    var liquidateassetsymbol: String? = null

}