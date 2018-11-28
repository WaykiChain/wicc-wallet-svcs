package com.waykichain.chain.po

import com.waykichain.chain.dict.WalletAddressType
import java.io.Serializable

class CoinAddressGenPO : Serializable {

    var walletAddressType:Int = WalletAddressType.CUSTOMER.code
    var symbol:String ?= null
}