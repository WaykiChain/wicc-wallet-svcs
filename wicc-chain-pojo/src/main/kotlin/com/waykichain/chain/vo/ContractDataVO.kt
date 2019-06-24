package com.waykichain.chain.vo

import java.io.Serializable

class ContractDataVO : Serializable {

    var contractRegid:String ?= null
    var key:String ?= null
    var value:Any ?= null
    var errorMsg:String ?=null

}