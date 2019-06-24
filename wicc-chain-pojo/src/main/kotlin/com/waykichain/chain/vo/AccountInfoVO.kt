package com.waykichain.chain.vo

import java.io.Serializable
import java.math.BigDecimal

class AccountInfoVO : Serializable {
    var address: String? = null
    var keyID: String? = null
    var regID: String? = null
    var publicKey: String? = null
    var minerPKey: String? = null
    var balance: BigDecimal? = null
    var votes: Long? = null
    var updateHeight: Int? = null
    var postion: String? = null
    var position: String?=null

}