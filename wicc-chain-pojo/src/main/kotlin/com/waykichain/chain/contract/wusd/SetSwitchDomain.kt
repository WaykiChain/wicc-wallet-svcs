package com.waykichain.chain.contract.wusd

import com.waykichain.chain.contract.util.ContractUtil
import com.waykichain.chain.contract.wusd.domain.WusdBaseDomain
import com.waykichain.chain.contract.wusd.domain.WusdConstants

class SetSwitchDomain: WusdBaseDomain() {

    var switchValue:String = WusdConstants.WUSD_CONTRACT_SWITCH_VALUE_CLOSE

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_SET_SWITCH
    }

    override fun serialize():String{
        var result = super.serialize()
        result += switchValue
        return result
    }

    override fun deserialize(contract:String):Int{
        var startIndex = super.deserialize(contract)
        var endIndex = startIndex + 1*2
        switchValue = contract.substring(startIndex,endIndex)
        return endIndex
    }

}