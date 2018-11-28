package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.BcWiccWalletAccountLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface BcWiccWalletAccountLogRepository : JpaRepository<BcWiccWalletAccountLog, Long>,
    QueryDslPredicateExecutor<BcWiccWalletAccountLog>
