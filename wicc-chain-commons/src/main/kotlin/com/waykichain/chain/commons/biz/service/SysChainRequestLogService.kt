package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.SysChainRequestLog

interface SysChainRequestLogService {
    fun getById(id:Long): SysChainRequestLog?

    fun save(sysChainRequestLog:SysChainRequestLog): SysChainRequestLog
}
