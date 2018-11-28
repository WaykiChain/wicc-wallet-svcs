package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.BcWiccWalletAddress
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface BcWiccWalletAddressRepository : JpaRepository<BcWiccWalletAddress, Long>,
    QueryDslPredicateExecutor<BcWiccWalletAddress>
