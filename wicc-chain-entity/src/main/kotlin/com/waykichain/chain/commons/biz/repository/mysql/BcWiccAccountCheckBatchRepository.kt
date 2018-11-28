package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.BcWiccAccountCheckBatch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface BcWiccAccountCheckBatchRepository : JpaRepository<BcWiccAccountCheckBatch, Long>,
    QueryDslPredicateExecutor<BcWiccAccountCheckBatch>
