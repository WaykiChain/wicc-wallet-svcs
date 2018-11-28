package com.waykichain.chain.contract.ico

import com.waykichain.chain.contract.util.ContractUtil

class IcoTransferDamain {

    var systype:String = IcoConstants.CONTRACT_ICO_SYSTYPE
    var type:String = IcoConstants.CONTRACT_ICO_TYPE_TRANSFER
    var version: String = "0000"
    var to:String = ""
    var amount:Long = 0

    fun serialize():String{
        var result = systype + type + version
        result += ContractUtil.toHexString(to)
        result += ContractUtil.to2HexString(amount)
        return result
    }

    fun deserialize(contract:String):Int{
        systype = contract.substring(0,2)
        type = contract.substring(2,4)
        version = contract.substring(4,8)

        var startIndex = 8
        var endIndex = startIndex + 34*2
        var toHex = contract.substring(startIndex,endIndex)
        to = ContractUtil.hexToString(toHex)

        startIndex = endIndex
        endIndex = startIndex + 8*2
        var amountStr = contract.substring(startIndex,endIndex)
        amount = java.lang.Long.parseLong(ContractUtil.upsidedownHex(amountStr), 16)
        return endIndex
    }

    override fun toString(): String {
        return "IcoTransferDamain(systype='$systype', type='$type', version='$version', to='$to', amount=$amount)"
    }

}