package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.SysConfig
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface SysConfigRepository : JpaRepository<SysConfig, Long>,
    QueryDslPredicateExecutor<SysConfig>
