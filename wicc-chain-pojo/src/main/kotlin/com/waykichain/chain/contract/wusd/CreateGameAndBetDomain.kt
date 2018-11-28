package com.waykichain.chain.contract.wusd

import com.waykichain.chain.contract.util.ContractUtil
import com.waykichain.chain.contract.wusd.domain.WusdConstants

class CreateGameAndBetDomain: TransferWithFeeByAdminDomain() {

    var betFrom:String = ""
    var betTo:String = ""
    var betAmount:Long = 0
    var betFee:Long = 0

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_CREATE_GAME_AND_BET
    }

    override fun serialize():String{
        var result = super.serialize()
        result += ContractUtil.toHexString(betFrom)

        if(betTo.length != 34){
            throw Exception("The to address length should be 34. but the actual to address length is " + betTo.length)
        }
        result += ContractUtil.toHexString(betTo)

        result += ContractUtil.to2HexString(betAmount)
        result += ContractUtil.to2HexString(betFee)
        return result
    }

    override fun deserialize(contract:String):Int{
        var startIndex = super.deserialize(contract)
        var endIndex = startIndex + 34*2
        var fromHex:String = contract.substring(startIndex,endIndex)
        betFrom = ContractUtil.hexToString(fromHex)

        startIndex = endIndex
        endIndex = startIndex + 34*2
        var toHex:String = contract.substring(startIndex,endIndex)
        betTo = ContractUtil.hexToString(toHex)

        startIndex = endIndex
        endIndex = startIndex + 8*2
        var amountStr = contract.substring(startIndex,endIndex)
        betAmount = java.lang.Long.parseLong(ContractUtil.upsidedownHex(amountStr), 16)

        startIndex = endIndex
        endIndex = startIndex + 8*2
        var feeStr = contract.substring(startIndex,endIndex)
        betFee = java.lang.Long.parseLong(ContractUtil.upsidedownHex(feeStr), 16)

        return endIndex
    }


}