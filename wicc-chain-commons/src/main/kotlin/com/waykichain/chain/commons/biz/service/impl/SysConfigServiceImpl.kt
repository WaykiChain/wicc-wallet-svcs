package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.bet.commons.biz.exception.BizException
import com.waykichain.chain.biz.domain.QSysConfig
import com.waykichain.chain.commons.biz.dict.ErrorCode
import com.waykichain.chain.commons.biz.repository.mysql.SysConfigRepository
import com.waykichain.chain.commons.biz.service.SysConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SysConfigServiceImpl : SysConfigService {

    override fun getByName(name: String): String {
        val sysConfig = sysConfigRepository.findOne(
                QSysConfig.sysConfig.name.eq(name))
                ?: throw BizException(com.waykichain.chain.commons.biz.dict.ErrorCode.PARAM_ERROR)
        return sysConfig.value!!
    }

    @Autowired
    private lateinit var sysConfigRepository: SysConfigRepository
}