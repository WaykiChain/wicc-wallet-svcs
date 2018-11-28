package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.SysChainMsgNotifySetting

interface SysChainMsgNotifySettingService {
    fun getById(id:Long): SysChainMsgNotifySetting?

    fun save(sysChainMsgNotifySetting:SysChainMsgNotifySetting): SysChainMsgNotifySetting
}
