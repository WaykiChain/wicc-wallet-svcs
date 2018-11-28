package com.waykichain.chain.contract.wusd.domain

import com.waykichain.chain.contract.util.ContractUtil

class SetExchangeRateDomain: WusdBaseDomain() {

    var exchangeRate:Long = 0

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_SET_EXCHANGE_RATE
    }

    override fun serialize():String{
        var result = super.serialize()
        result += ContractUtil.to2HexString(exchangeRate)
        return result
    }

    override fun deserialize(contract:String):Int{
        var startIndex = super.deserialize(contract)
        var endIndex = startIndex + 8*2
        var exchangeRateStr = contract.substring(startIndex,endIndex)
        exchangeRate = java.lang.Long.parseLong(ContractUtil.upsidedownHex(exchangeRateStr), 16)

        return endIndex
    }

    override fun toString(): String {
        return super.toString() + "SetExchangeRateDomain(exchangeRate=$exchangeRate)"
    }

}