package com.waykichain.chain.contract.wusd

import com.waykichain.chain.contract.util.ContractUtil
import com.waykichain.chain.contract.wusd.domain.WusdBaseDomain
import com.waykichain.chain.contract.wusd.domain.WusdConstants

class TransferTokenByUserDomain: WusdBaseDomain() {

    var to:String = ""
    var amount:Long = 0

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_TRANSFER_TOKEN_By_User
    }

    override fun serialize():String{
        var result = super.serialize()
        result += ContractUtil.toHexString(to)
        result += ContractUtil.to2HexString(amount)
        return result
    }

    override fun deserialize(contract:String):Int{
        var startIndex = super.deserialize(contract)
        var endIndex = startIndex + 34*2
        var toHex = contract.substring(startIndex,endIndex)
        to = ContractUtil.hexToString(toHex)

        startIndex = endIndex
        endIndex = startIndex + 8*2
        var amountStr = contract.substring(startIndex,endIndex)
        amount = java.lang.Long.parseLong(ContractUtil.upsidedownHex(amountStr), 16)

        return endIndex
    }

}