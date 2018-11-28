package com.waykichain.chain.filter

import com.waykichain.chian.config.RepeatableReadServletRequest
import javax.servlet.*
import javax.servlet.http.HttpServletRequest

open class CustomerServletFilter : Filter {

    override fun destroy() {
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val req = RepeatableReadServletRequest(request as HttpServletRequest)

        chain.doFilter(req, response)
    }

    override fun init(filterConfig: FilterConfig?) {
    }


}