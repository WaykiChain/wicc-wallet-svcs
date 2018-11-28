package com.waykichain.bet.commons.biz.exception

import com.waykichain.chain.commons.biz.dict.ErrorCode


/**
 * Created by richardchen on 4/22/17.
 */
class BizException(val code: Int, val msg: String) : RuntimeException(msg) {

    constructor(errorCode: com.waykichain.chain.commons.biz.dict.ErrorCode) : this(errorCode.code, errorCode.msg)
}
