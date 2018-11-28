package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.BcWiccBlock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface BcWiccBlockRepository : JpaRepository<BcWiccBlock, Long>,
    QueryDslPredicateExecutor<BcWiccBlock>
