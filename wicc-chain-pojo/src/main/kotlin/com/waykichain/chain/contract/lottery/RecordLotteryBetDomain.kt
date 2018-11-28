package com.waykichain.chain.contract.lottery

import com.waykichain.chain.contract.util.ContractUtil
import com.waykichain.chain.contract.wusd.domain.WusdBaseDomain

open class RecordLotteryBetDomain: WusdBaseDomain() {

    var userId:String = ""
    var keepContent: String = "0000000000"
    var lotteryInfo: String = ""

    init {
        type = LotteryConstants.WUSD_CONTRACT_TYPE_RECORD_LOTTERY_BET
    }

    override fun serialize():String{
        var result = super.serialize()
        if(userId.length != 10){
            throw Exception("userId length should be 10")
        }
        result +=  ContractUtil.toHexString(userId)
        result +=  ContractUtil.toHexString(keepContent)
        result +=  ContractUtil.utf8ToHexString(lotteryInfo, 0)
        return result
    }

    override fun deserialize(contract:String):Int{
        var startIndex = super.deserialize(contract)
        var endIndex = startIndex + 10*2
        var userIdHex = contract.substring(startIndex,endIndex)
        userId = ContractUtil.hexToString(userIdHex)

        startIndex = endIndex + 10*2
        var lotteryInfoHex = contract.substring(startIndex)
        lotteryInfo = ContractUtil.hexToUTF8(lotteryInfoHex)
        return endIndex
    }

}