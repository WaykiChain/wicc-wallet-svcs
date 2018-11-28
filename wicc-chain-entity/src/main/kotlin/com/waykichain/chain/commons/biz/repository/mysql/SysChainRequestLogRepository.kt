package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.SysChainRequestLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface SysChainRequestLogRepository : JpaRepository<SysChainRequestLog, Long>,
    QueryDslPredicateExecutor<SysChainRequestLog>
