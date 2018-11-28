package com.waykichain.chain.contract.wusd.domain

import com.waykichain.chain.contract.util.ContractUtil


class ExchangeTokenDomain : WusdBaseDomain() {

    var exchangeRate:Long = 0
    var exchangeTokenAmount:Long = 0

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_EXCHANGE_TOKEN
    }

    override fun serialize():String{
        var result = super.serialize()
        result += ContractUtil.to2HexString(exchangeRate)
        result += ContractUtil.to2HexString(exchangeTokenAmount)
        return result
    }

    override fun deserialize(contract:String):Int{
        var startIndex = super.deserialize(contract)
        var endIndex = startIndex + 8*2
        var exchangeRateStr = contract.substring(startIndex,endIndex)
        exchangeRate = java.lang.Long.parseLong(ContractUtil.upsidedownHex(exchangeRateStr), 16)

        startIndex = endIndex
        endIndex = startIndex + 8*2
        var exchangeTokenCountStr = contract.substring(startIndex,endIndex)
        exchangeTokenAmount = java.lang.Long.parseLong(ContractUtil.upsidedownHex(exchangeTokenCountStr), 16)

        return endIndex
    }

    override fun toString(): String {
        return super.toString() +  " ExhangeTokenDomain(exchangeRate=$exchangeRate, exchangeTokenAmount=$exchangeTokenAmount)"
    }
}