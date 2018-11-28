package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.BcWiccWalletAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor

interface BcWiccWalletAccountRepository : JpaRepository<BcWiccWalletAccount, Long>,
    QueryDslPredicateExecutor<BcWiccWalletAccount>
