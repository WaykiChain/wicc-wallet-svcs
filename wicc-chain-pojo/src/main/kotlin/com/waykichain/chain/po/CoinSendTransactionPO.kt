package com.waykichain.chain.po

import java.io.Serializable
import java.math.BigDecimal

class CoinSendTransactionPO : Serializable {
    var requestUUID:String ?= null
    var symbol:String ?= null
    var sendAddress: String? = null
    var recvAddress:String ?= null
    var amount: BigDecimal?= null       //花费的总金额
    var fee: BigDecimal?= null ///<NO used
}