package com.waykichain.chain.contract.wusd

import com.waykichain.chain.contract.util.ContractUtil
import com.waykichain.chain.contract.wusd.domain.WusdConstants

class UpdateGameAndBetDomain: TransferWithFeeByAdminDomain() {

    var updateGameAddress:String = ""
    var updateGameFee:Long = 0
    var gameId:String = ""

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_UPDATE_GAME_AND_BET
    }

    override fun serialize():String{
        var result = super.serialize()
        result += ContractUtil.toHexString(updateGameAddress)
        result += ContractUtil.to2HexString(updateGameFee)
        result += ContractUtil.toHexString(gameId)
        return result
    }

    override fun deserialize(contract:String):Int{
        var startIndex = super.deserialize(contract)
        var endIndex = startIndex + 34*2
        var updateGameAddressHex = contract.substring(startIndex,endIndex)
        updateGameAddress = ContractUtil.hexToString(updateGameAddressHex)

        startIndex = endIndex
        endIndex = startIndex + 8*2
        var feeHex = contract.substring(startIndex,endIndex)
        updateGameFee = java.lang.Long.parseLong(ContractUtil.upsidedownHex(feeHex), 16)

        startIndex = endIndex
        endIndex = startIndex + 34*2
        var gameIdHex = contract.substring(startIndex,endIndex)
        gameId = ContractUtil.hexToString(gameIdHex)

        return endIndex
    }

}