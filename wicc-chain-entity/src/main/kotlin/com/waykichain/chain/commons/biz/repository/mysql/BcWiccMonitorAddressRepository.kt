package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.BcWiccMonitorAddress
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface BcWiccMonitorAddressRepository : JpaRepository<BcWiccMonitorAddress, Long>,
    QueryDslPredicateExecutor<BcWiccMonitorAddress>
