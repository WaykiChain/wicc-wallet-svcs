package com.waykichain.chain.commons.biz.utils

import com.google.gson.Gson
import com.waykichain.chain.commons.biz.dict.ErrorCodeDefiniteDict
import com.waykichain.chain.commons.biz.dict.ErrorCodeDefiniteType
import com.waykichain.commons.base.BizResponse
import org.slf4j.LoggerFactory

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/7/2 21:32
 *
 * @Description:    错误码转换
 *
 */
class ErrorCodeTranferUtil {

    companion object {

        private val logger = LoggerFactory.getLogger(javaClass)

        fun getErrorCodeFromOriginal(response: BizResponse<Any>): BizResponse<Any> {

            logger.info("[TranFer ErrorCode] Original response = ${Gson().toJson(response)}")

            try {
                loop@ for (errorCode in ErrorCodeDefiniteDict.values()) {
                    if (errorCode.originalCode == response.code) {
                        when (errorCode.type) {
                            ErrorCodeDefiniteType.COMPLETE_REPLACEMENT.type -> {
                                if (errorCode.originalMsg == response.msg) {
                                    response.code = errorCode.code
                                    response.msg = errorCode.msg
                                    break@loop
                                }
                            }
                            ErrorCodeDefiniteType.PARTIAL_REPLACEMENT_FROM_FRONT.type -> {
                                if (response.msg!!.startsWith(errorCode.originalMsg)) {
                                    response.code = errorCode.code
                                    response.msg = response.msg!!.replace(errorCode.originalMsg, errorCode.msg, true)
                                    break@loop
                                }
                            }
                            else -> {}
                        }
                    }
                }
            } catch (e: Exception) {

            }
            return response
        }

    }



}