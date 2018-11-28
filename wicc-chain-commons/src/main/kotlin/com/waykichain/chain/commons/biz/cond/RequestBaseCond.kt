package com.waykichain.chain.commons.biz.cond

import java.io.Serializable

open class RequestBaseCond :Serializable {
    var offset: Int?= null
    var size: Int?= null

    var pageIndex:Int?= null
    var pageSize:Int?=null
}