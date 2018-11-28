package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.BcWiccBlockBak
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface BcWiccBlockBakRepository : JpaRepository<BcWiccBlockBak, Long>,
    QueryDslPredicateExecutor<BcWiccBlockBak>
