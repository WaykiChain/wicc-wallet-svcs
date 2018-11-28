package com.waykichain.chain.commons.biz.vo

import java.io.Serializable
import java.util.*

class HttpLogMessage : Serializable {

    var createdAt: Date? = null

    var customerId: Long? = null

    var deviceUuid: String? = null

    var errorCode: Int? = null

    var method: String? = null

    var module: String? = null

    var requestIp: String? = null

    var requestParmas: String? = null

    var requestUri: String? = null

    var requestUrl: String? = null

    var requestUuid: String? = null

    var response: String? = null

    var responseTime: Long? = null

    var stackTrace: String? = null

    var timestamp: Long? = null


}