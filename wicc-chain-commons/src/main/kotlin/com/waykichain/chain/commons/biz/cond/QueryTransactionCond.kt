package com.waykichain.chain.commons.biz.cond

class QueryTransactionCond {
    var address:String ?= null
    var startNumber:Int ?=null
    var txid:String?=null
    var tranDirection:Int ?=null
    var pageSize:Int =20
    var currentPage:Int =1
    var txtype:String? = null
    var coinsymbol:String? = null
}