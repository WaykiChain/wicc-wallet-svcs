package com.waykichain.chain.commons.biz.domain

/**
 * Created by Joss on 2017/7/8.
 */
open class BizResponse<T>(var code: Int, var msg: String?, var data: T?) {

    constructor() : this(0, "success", null)

    constructor(data: T?) : this(0, "success", data)

    constructor(code: Int, msg: String?) : this(code, msg, null)

}