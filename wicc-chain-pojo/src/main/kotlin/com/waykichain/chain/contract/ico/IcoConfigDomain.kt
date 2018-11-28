package com.waykichain.chain.contract.ico


class IcoConfigDomain {

    var systype:String = IcoConstants.CONTRACT_ICO_SYSTYPE
    var type:String = IcoConstants.CONTRACT_ICO_TYPE_CONFIG

    fun serialize():String{
        return systype + type
    }

    fun deserialize(contract:String):Int{
        systype = contract.substring(0,2)
        type = contract.substring(2,4)
        return 8
    }

    override fun toString(): String {
        return " BaseContractDomain(systype='$systype', type='$type')"
    }
}