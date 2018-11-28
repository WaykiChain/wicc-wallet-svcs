package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.commons.biz.service.SysChainMsgNotifySettingService
import com.waykichain.chain.biz.domain.SysChainMsgNotifySetting
import com.waykichain.chain.commons.biz.repository.mysql.SysChainMsgNotifySettingRepository
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class SysChainMsgNotifySettingServiceImpl: SysChainMsgNotifySettingService {

    override fun getById(id:Long): SysChainMsgNotifySetting? {
        return sysChainMsgNotifySettingRepository.findOne(id)
    }

    override fun save(sysChainMsgNotifySetting:SysChainMsgNotifySetting): SysChainMsgNotifySetting {
        return sysChainMsgNotifySettingRepository.saveAndFlush(sysChainMsgNotifySetting)
    }

    @Autowired lateinit var sysChainMsgNotifySettingRepository: SysChainMsgNotifySettingRepository
}
