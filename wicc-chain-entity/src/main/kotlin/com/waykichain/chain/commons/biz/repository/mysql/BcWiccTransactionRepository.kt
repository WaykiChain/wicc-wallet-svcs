package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.BcWiccTransaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface BcWiccTransactionRepository : JpaRepository<BcWiccTransaction, Long>,
    QueryDslPredicateExecutor<BcWiccTransaction>
