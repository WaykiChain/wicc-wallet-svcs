package com.waykichain.chian.config

import com.waykichain.bet.commons.biz.exception.BizException
import com.waykichain.chain.commons.biz.dict.ErrorCode
import org.slf4j.LoggerFactory
import java.io.ByteArrayInputStream
import javax.servlet.ReadListener
import javax.servlet.ServletInputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper

open class RepeatableReadServletRequest(request: HttpServletRequest) : HttpServletRequestWrapper(request) {

    private val logger = LoggerFactory.getLogger(javaClass)
    private var requestBody: String = ""

    init {
        request.setAttribute("request_timestamp", System.currentTimeMillis())

        requestBody = getRequestBody(request)
    }

    override fun getInputStream(): ServletInputStream {

        return CustomerServletInputStream(ByteArrayInputStream(requestBody.toByteArray()))
    }

    private fun getRequestBody(request: HttpServletRequest): String {

        try {
            val stringBuffer = StringBuffer()

            val buf: ByteArray = kotlin.ByteArray(1024 * 100)
            val inputStream = request.inputStream
            var len = 0
            while (inputStream.read(buf).apply { len = this } != -1) {
                stringBuffer.append(String(buf, 0, len))
            }

            return stringBuffer.toString()

        } catch (e: Exception) {
            logger.error(" getRequestBody exception", e)
            throw BizException(ErrorCode.PARAM_ERROR)
        }
    }
}

class CustomerServletInputStream(val inputStream: ByteArrayInputStream) : ServletInputStream() {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun isReady(): Boolean {
        return true
    }

    override fun isFinished(): Boolean {
        return inputStream.available() == 0
    }

    override fun read(): Int {
        return inputStream.read()
    }

    override fun setReadListener(readListener: ReadListener) {
    }
}