package com.waykichain.chain.contract.lockposition

import com.waykichain.chain.contract.util.ContractUtil

class UnlockPositionByAdminDomain {

    var systype:String = "f2"
    var type:String = "04"
    var address:String = ""
    var unlockCount:Long = 0
    var unlockHeight:Int = 0
    var lockTxHash:String = ""

    fun serialize():String{
        var result = systype + type
        result += ContractUtil.toHexString(address)
        result += ContractUtil.to2HexString(unlockCount)
        result += ContractUtil.to2HexString(unlockHeight)
        result += ContractUtil.toHexString(lockTxHash)
        return result
    }

    fun deserialize(contract:String):Int{
        systype = contract.substring(0,2)
        type = contract.substring(2,4)

        var startIndex = 4
        var endIndex = startIndex + 34*2
        var addressHex = contract.substring(startIndex,endIndex)
        address = ContractUtil.hexToString(addressHex)

        startIndex = endIndex
        endIndex = startIndex + 8*2
        var unlockCountHex = contract.substring(startIndex,endIndex)
        System.out.println("unlockCountHex:" + unlockCountHex)
        unlockCount = java.lang.Long.parseLong(ContractUtil.upsidedownHex(unlockCountHex), 16)

        startIndex = endIndex
        endIndex = startIndex + 4*2
        var unlockHeightHex = contract.substring(startIndex,endIndex)
        unlockHeight = java.lang.Integer.parseInt(ContractUtil.upsidedownHex(unlockHeightHex), 16)

        startIndex = endIndex
        endIndex = startIndex + 64*2
        var lockTxHashHex = contract.substring(startIndex,endIndex)
        lockTxHash = ContractUtil.hexToString(lockTxHashHex)

        return endIndex
    }

    override fun toString(): String {
        return "UnlockPositionByAdminDomain(systype='$systype', type='$type', address='$address', unlockCount=$unlockCount, unlockHeight=$unlockHeight, lockTxHash='$lockTxHash')"
    }

}