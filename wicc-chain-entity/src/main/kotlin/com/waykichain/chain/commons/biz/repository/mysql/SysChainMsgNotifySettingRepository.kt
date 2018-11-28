package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.SysChainMsgNotifySetting
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface SysChainMsgNotifySettingRepository : JpaRepository<SysChainMsgNotifySetting, Long>,
    QueryDslPredicateExecutor<SysChainMsgNotifySetting>
