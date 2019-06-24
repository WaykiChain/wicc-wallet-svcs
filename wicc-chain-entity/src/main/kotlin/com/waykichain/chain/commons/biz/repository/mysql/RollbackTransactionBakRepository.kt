package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.RollbackTransactionBak
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface RollbackTransactionBakRepository : JpaRepository<RollbackTransactionBak, Long>,
    QueryDslPredicateExecutor<RollbackTransactionBak>
