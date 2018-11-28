package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.BcWiccContractInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface BcWiccContractInfoRepository : JpaRepository<BcWiccContractInfo, Long>,
    QueryDslPredicateExecutor<BcWiccContractInfo>
