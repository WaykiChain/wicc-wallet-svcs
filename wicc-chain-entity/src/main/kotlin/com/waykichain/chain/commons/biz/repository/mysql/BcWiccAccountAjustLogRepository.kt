package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.BcWiccAccountAjustLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface BcWiccAccountAjustLogRepository : JpaRepository<BcWiccAccountAjustLog, Long>,
    QueryDslPredicateExecutor<BcWiccAccountAjustLog>
