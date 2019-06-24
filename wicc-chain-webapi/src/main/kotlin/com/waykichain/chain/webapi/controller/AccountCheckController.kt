package com.waykichain.chain.webapi.controller

import com.waykichain.chain.biz.domain.QBcWiccAccountCheckBatch
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccAccountCheckBatchRepository
import com.waykichain.chain.commons.biz.xservice.AccountCheckXService
import com.waykichain.chain.dict.AccountCheckBatchStatus
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class AccountCheckController {


    @GetMapping("/gen/log")
    @ApiOperation(value = "生成对账记录", notes = "生成对账记录")
    fun genCheckLog() {
        val batches = bcWiccAccountCheckBatchRepository.findAll(
                QBcWiccAccountCheckBatch.bcWiccAccountCheckBatch.status.eq(AccountCheckBatchStatus.SUBMIT.code))
        var batch = batches.firstOrNull()

        accountCheckXService.onCheck(batch!!)

    }

    @GetMapping("/ajust/log")
    @ApiOperation(value = "生成对账记录", notes = "生成对账记录")
    fun onAjuestLog() {
        val batches = bcWiccAccountCheckBatchRepository.findAll(
                QBcWiccAccountCheckBatch.bcWiccAccountCheckBatch.status.eq(AccountCheckBatchStatus.CHECK_FINISHED.code))
        var batch = batches.firstOrNull()

        accountCheckXService.onAjustAccountLog(batch)

    }

    val logger = LoggerFactory.getLogger(javaClass)
    @Autowired lateinit var bcWiccAccountCheckBatchRepository:BcWiccAccountCheckBatchRepository

    @Autowired lateinit var accountCheckXService: AccountCheckXService
}