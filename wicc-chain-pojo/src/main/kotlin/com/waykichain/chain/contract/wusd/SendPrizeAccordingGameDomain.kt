package com.waykichain.chain.contract.wusd

import com.waykichain.chain.contract.util.ContractUtil
import com.waykichain.chain.contract.wusd.domain.WusdBaseDomain
import com.waykichain.chain.contract.wusd.domain.WusdConstants

class SendPrizeAccordingGameDomain: WusdBaseDomain(){

    var gameId:String = ""
    var prizeContent:String = "12345678901234567890"
    var sendPrizeCount:Int = 0
    var sendPrizeList:MutableList<TransferModel> = ArrayList()

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_SEND_PRIZE_ACCORDING_GAME
    }

    override fun serialize():String{
        var result = super.serialize()

        if(prizeContent.length!=20){
            throw Exception("prizeContent length should be 20.")
        }
        result +=  ContractUtil.toHexString(prizeContent)

        result += ContractUtil.toHexString(gameId)
        result +=  ContractUtil.to2HexString(sendPrizeCount)
        for(i in 0..(sendPrizeCount-1)){
            var transferModel: TransferModel =  sendPrizeList.get(i)

            if(transferModel.toAddress.length != 34){
                throw Exception("Send to address length should be 34.")
            }
            result += ContractUtil.toHexString(transferModel.toAddress)

            result += ContractUtil.to2HexString(transferModel.amount)
        }
        return result
    }

    override fun deserialize(contract:String):Int{
        var startIndex = super.deserialize(contract)
        var endIndex = startIndex + 20*2
        var prizeResultHex = contract.substring(startIndex,endIndex)
        prizeContent = ContractUtil.hexToString(prizeResultHex)

        startIndex = endIndex
        endIndex = startIndex + 34*2
        var gameIdHex = contract.substring(startIndex,endIndex)
        gameId = ContractUtil.hexToString(gameIdHex)

        startIndex = endIndex
        endIndex = startIndex + 4*2
        var sendPrizeCountHex = contract.substring(startIndex,endIndex)
        sendPrizeCount = java.lang.Integer.parseInt(ContractUtil.upsidedownHex(sendPrizeCountHex), 16)

        for(i in 1..sendPrizeCount){
            var transferModel = TransferModel()
            startIndex = endIndex
            endIndex = startIndex + 34*2
            var toHex:String = contract.substring(startIndex,endIndex)
            transferModel.toAddress = ContractUtil.hexToString(toHex)
            startIndex = endIndex
            endIndex = startIndex + 8*2
            var amountHex = contract.substring(startIndex,endIndex)
            transferModel.amount = java.lang.Long.parseLong(ContractUtil.upsidedownHex(amountHex), 16)
            sendPrizeList.add(transferModel)
        }

        return endIndex
    }

}