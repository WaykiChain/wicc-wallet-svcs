package com.waykichain.chain.contract.wusd

import com.waykichain.chain.contract.util.ContractUtil
import com.waykichain.chain.contract.wusd.domain.WusdBaseDomain
import com.waykichain.chain.contract.wusd.domain.WusdConstants

class BatchBetByAdminDomain: WusdBaseDomain() {

    var betCount:Int = 0
    var betList:MutableList<BetInfoModel> = ArrayList()

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_BATCH_BET_BY_ADMIN
    }

    override fun serialize():String{
        var result = super.serialize()
        result +=  ContractUtil.to2HexString(betCount)
        for(i in 0..(betCount-1)){
            var betInfoModel: BetInfoModel =  betList.get(i)
            var betStr = ContractUtil.toHexString(betInfoModel.fromAddress)

            if(betInfoModel.toAddress.length != 34){
                throw Exception("The to address length should be 34. but the actual to address length is " + betInfoModel.toAddress.length)
            }
            betStr += ContractUtil.toHexString(betInfoModel.toAddress)

            betStr += ContractUtil.to2HexString(betInfoModel.amount)
            betStr += ContractUtil.to2HexString(betInfoModel.fee)

            betStr += ContractUtil.to2HexString(betInfoModel.betRule)
            if(betInfoModel.betComment.length != 8){
                throw Exception("betComment length should be 8.")
            }
            betStr += ContractUtil.toHexString(betInfoModel.betComment)
            betStr += ContractUtil.toHexString(Character.toString(betInfoModel.betOption))
            betStr += ContractUtil.toHexString(betInfoModel.betOdds)

            result += ContractUtil.fillZero(betStr, 124*2)
        }
        return result
    }

    override fun deserialize(contract:String):Int {
        var startIndex = super.deserialize(contract)
        var endIndex = startIndex + 4*2
        var betCountHex = contract.substring(startIndex,endIndex)
        betCount = Integer.parseInt(ContractUtil.upsidedownHex(betCountHex),16)

        for(i in 1..betCount){
            var betInfoModel = BetInfoModel()
            startIndex = endIndex
            endIndex = startIndex + 34*2
            var fromHex:String = contract.substring(startIndex,endIndex)
            betInfoModel.fromAddress = ContractUtil.hexToString(fromHex)

            startIndex = endIndex
            endIndex = startIndex + 34*2
            var toHex:String = contract.substring(startIndex,endIndex)
            betInfoModel.toAddress = ContractUtil.hexToString(toHex)

            startIndex = endIndex
            endIndex = startIndex + 8*2
            var amountHex = contract.substring(startIndex,endIndex)
            betInfoModel.amount = java.lang.Long.parseLong(ContractUtil.upsidedownHex(amountHex), 16)

            startIndex = endIndex
            endIndex = startIndex + 8*2
            var feeHex = contract.substring(startIndex,endIndex)
            betInfoModel.fee = java.lang.Long.parseLong(ContractUtil.upsidedownHex(feeHex), 16)

            startIndex = endIndex
            endIndex = startIndex + 4*2
            var betRuleHex = contract.substring(startIndex,endIndex)
            betInfoModel.betRule = java.lang.Integer.parseInt(ContractUtil.upsidedownHex(betRuleHex), 16)

            startIndex = endIndex
            endIndex = startIndex + 8*2
            var betCommentHex = contract.substring(startIndex,endIndex)
            betInfoModel.betComment = ContractUtil.hexToString(betCommentHex)

            startIndex = endIndex
            endIndex = startIndex + 1*2
            var betOptionHex = contract.substring(startIndex,endIndex)
            betInfoModel.betOption = ContractUtil.hexToString(betOptionHex).toCharArray()[0]

            startIndex = endIndex
            endIndex = startIndex + 8*2
            var betOddsHex = contract.substring(startIndex,endIndex)
            betInfoModel.betOdds = ContractUtil.hexToString(betOddsHex)

            startIndex = endIndex
            endIndex = startIndex + 19*2

            betList.add(betInfoModel)
        }

        return endIndex
    }

    override fun toString(): String {
        return "BatchBetByAdminDomain(betCount=$betCount, betList=$betList)"
    }


}