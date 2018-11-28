package com.waykichain.chain.contract.wusd

import com.waykichain.chain.contract.util.ContractUtil
import com.waykichain.chain.contract.wusd.domain.WusdBaseDomain

open class TransferWithoutFeeDomain: WusdBaseDomain() {

    var from: String = ""
    var to: String = ""
    var amount: Long = 0

    override fun serialize(): String {
        var result = super.serialize()
        result += ContractUtil.toHexString(from)
        if(to.length != 34){
            throw Exception("The to address length should be 34. but the actual to address length is " + to.length)
        }
        result += ContractUtil.toHexString(to)
        result += ContractUtil.to2HexString(amount)
        return result
    }

    override fun deserialize(contract: String): Int {
        var startIndex = super.deserialize(contract)
        var endIndex = startIndex + 34*2
        var fromHex: String = contract.substring(startIndex, endIndex)
        from = ContractUtil.hexToString(fromHex)

        startIndex = endIndex
        endIndex = startIndex + 34*2
        var toHex: String = contract.substring(startIndex, endIndex)
        to = ContractUtil.hexToString(toHex)

        startIndex = endIndex
        endIndex = startIndex + 8*2
        var amountStr = contract.substring(startIndex, endIndex)
        amount = java.lang.Long.parseLong(ContractUtil.upsidedownHex(amountStr), 16)

        return endIndex
    }

}