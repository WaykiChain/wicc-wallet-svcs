package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.BcWiccAlertLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface BcWiccAlertLogRepository : JpaRepository<BcWiccAlertLog, Long>,
    QueryDslPredicateExecutor<BcWiccAlertLog>
