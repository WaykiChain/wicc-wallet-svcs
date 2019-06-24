package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.RollbackBlockBak
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface RollbackBlockBakRepository : JpaRepository<RollbackBlockBak, Long>,
    QueryDslPredicateExecutor<RollbackBlockBak>
