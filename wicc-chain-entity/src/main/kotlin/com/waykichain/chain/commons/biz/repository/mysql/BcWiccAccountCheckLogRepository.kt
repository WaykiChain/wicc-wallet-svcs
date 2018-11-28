package com.waykichain.chain.commons.biz.repository.mysql

import com.waykichain.chain.biz.domain.BcWiccAccountCheckLog
import org.apache.ibatis.annotations.Param
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.querydsl.QueryDslPredicateExecutor
import javax.transaction.Transactional

interface BcWiccAccountCheckLogRepository : JpaRepository<BcWiccAccountCheckLog, Long>,
    QueryDslPredicateExecutor<BcWiccAccountCheckLog> {

    @Modifying
    @Transactional
    @Query("update bc_wicc_account_check_log set status = ?2 where id = ?1 and status = ?3", nativeQuery = true)
    fun updateByLockStatus(@Param("id")id:Long, @Param("status")status:Int, @Param("lockStatus")lockStatus:Int):Int
}
