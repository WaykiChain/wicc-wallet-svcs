package com.waykichain.chain.contract.wusd

import com.waykichain.chain.contract.util.ContractUtil
import com.waykichain.chain.contract.wusd.domain.WusdBaseDomain
import com.waykichain.chain.contract.wusd.domain.WusdConstants

class ExchangeWiccByAdminDomain: WusdBaseDomain() {

    var exchangeAddress:String = ""
    var exchangeTokenAmount:Long = 0
    var exchangeRate:Long = 0
    var exchangeWiccAmount:Long = 0
    var fee:Long = 0

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_EXCHAGE_WICC_BY_ADMIN
    }

    override fun serialize():String{
        var result = super.serialize()
        result += ContractUtil.toHexString(exchangeAddress)
        result += ContractUtil.to2HexString(exchangeTokenAmount)
        result += ContractUtil.to2HexString(exchangeRate)
        result += ContractUtil.to2HexString(exchangeWiccAmount)
        result += ContractUtil.to2HexString(fee)
        return result
    }

    override fun deserialize(contract:String):Int{
        var startIndex = super.deserialize(contract)
        var endIndex = startIndex + 34*2
        var exchangeAddressHex = contract.substring(startIndex,endIndex)
        exchangeAddress = ContractUtil.hexToString(exchangeAddressHex)

        startIndex = endIndex
        endIndex = startIndex + 8*2
        var exchangeTokenAmountHex = contract.substring(startIndex,endIndex)
        exchangeTokenAmount = java.lang.Long.parseLong(ContractUtil.upsidedownHex(exchangeTokenAmountHex), 16)

        startIndex = endIndex
        endIndex = startIndex + 8*2
        var exchangeRateHex = contract.substring(startIndex,endIndex)
        exchangeRate = java.lang.Long.parseLong(ContractUtil.upsidedownHex(exchangeRateHex), 16)

        startIndex = endIndex
        endIndex = startIndex + 8*2
        var exchangeWiccAmountHex = contract.substring(startIndex,endIndex)
        exchangeWiccAmount = java.lang.Long.parseLong(ContractUtil.upsidedownHex(exchangeWiccAmountHex), 16)

        startIndex = endIndex
        endIndex = startIndex + 8*2
        var feeHex = contract.substring(startIndex,endIndex)
        fee = java.lang.Long.parseLong(ContractUtil.upsidedownHex(feeHex), 16)

        return endIndex
    }

}