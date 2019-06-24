package com.waykichain.chain.contract.wusd.domain

import com.waykichain.chain.contract.util.ContractUtil

class SetAdminDomain: WusdBaseDomain()  {

    var adminAddr:String = ""
    var adminType:String="10"

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_SET_ADMIN
    }

    override fun serialize():String{
        var result = super.serialize()
        result += ContractUtil.toHexString(adminAddr)
        if(adminType.length!=2){
            throw Exception("symbol length should be 2.")
        }
        result += adminType
        return result
    }

    override fun deserialize(contract:String):Int{
        var startIndex = super.deserialize(contract)
        var endIndex = startIndex + 34*2
        var adminAddrHex = contract.substring(startIndex,endIndex)
        adminAddr = ContractUtil.hexToString(adminAddrHex)

        startIndex = endIndex
        endIndex = startIndex + 2
        adminType = contract.substring(startIndex,endIndex)

        return endIndex
    }

    override fun toString(): String {
        return "SetAdminDomain(adminAddr='$adminAddr', adminType='$adminType')"
    }

}