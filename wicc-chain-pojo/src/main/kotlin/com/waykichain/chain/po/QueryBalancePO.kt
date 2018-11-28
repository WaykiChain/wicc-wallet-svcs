package com.waykichain.chain.po

import java.io.Serializable

class QueryBalancePO : Serializable {
    var symbol:String ?= null
    var address:String ?= null
}