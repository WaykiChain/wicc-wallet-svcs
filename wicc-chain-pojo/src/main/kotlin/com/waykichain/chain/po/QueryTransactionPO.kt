package com.waykichain.chain.po

import java.io.Serializable

class QueryTransactionPO:Serializable {
    var symbol:String ?= null
    var recvAddress:String ?=null
    var startNumber:Int?= null
}