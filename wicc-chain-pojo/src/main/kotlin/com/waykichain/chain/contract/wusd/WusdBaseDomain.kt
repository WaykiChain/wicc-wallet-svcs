package com.waykichain.chain.contract.wusd.domain

open class WusdBaseDomain {

    var systype:String = WusdConstants.WUSD_CONTRACT_SYSTYPE
    var type:String = "00"
    var version: String = "0000"

    open fun serialize():String{
        return systype + type + version
    }

    open fun deserialize(contract:String):Int{
        systype = contract.substring(0,2)
        type = contract.substring(2,4)
        version = contract.substring(4,8)
        return 8
    }

    override fun toString(): String {
        return " BaseContractDomain(systype='$systype', type='$type', version='$version')"
    }

}