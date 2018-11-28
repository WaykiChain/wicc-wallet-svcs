package com.waykichain.chain.contract.wusd.domain

import com.waykichain.chain.contract.util.ContractUtil

class SetAdminDomain: WusdBaseDomain()  {

    var adminAddr:String = ""
    // the contract name symbol
    var symbol:String="0000"

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_SET_ADMIN
    }

    override fun serialize():String{
        var result = super.serialize()
        result += ContractUtil.toHexString(adminAddr)
/*        if(symbol.length!=4){
            throw Exception("symbol length should be 4.")
        }*/
        result += ContractUtil.toHexString(symbol)
        return result
    }

    override fun deserialize(contract:String):Int{
        var startIndex = super.deserialize(contract)
        var endIndex = startIndex + 34*2
        var adminAddrHex = contract.substring(startIndex,endIndex)
        adminAddr = ContractUtil.hexToString(adminAddrHex)

        startIndex = endIndex
        endIndex = startIndex + 5*2
        var symbolHex = contract.substring(startIndex,endIndex)
        symbol = ContractUtil.hexToString(symbolHex)

        return endIndex
    }

    override fun toString(): String {
        return "SetAdminDomain(adminAddr='$adminAddr', symbol='$symbol')"
    }

}