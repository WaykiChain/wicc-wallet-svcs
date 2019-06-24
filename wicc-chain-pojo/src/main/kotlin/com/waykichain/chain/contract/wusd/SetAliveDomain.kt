package com.waykichain.chain.contract.wusd

import com.waykichain.chain.contract.util.ContractUtil
import com.waykichain.chain.contract.wusd.domain.WusdBaseDomain
import com.waykichain.chain.contract.wusd.domain.WusdConstants

class SetAliveDomain: WusdBaseDomain()  {

    var isAlive:String = ""

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_SET_ALIVE
    }

    override fun serialize():String{
        var result = super.serialize()
        result += isAlive
        return result
    }

    override fun deserialize(contract:String):Int{
        var startIndex = super.deserialize(contract)
        var endIndex = startIndex + 1*2
        isAlive = contract.substring(startIndex,endIndex)
        return endIndex
    }

}