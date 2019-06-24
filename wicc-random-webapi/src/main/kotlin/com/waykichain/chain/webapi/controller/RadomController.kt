package com.waykichain.chain.webapi.controller

import com.waykichain.chain.commons.biz.xservice.CoinHandler
import com.waykichain.chain.po.*
import com.waykichain.chain.vo.*
import com.waykichain.commons.base.BizResponse
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/radom")
class RadomController {

    @PostMapping("/balanceByLog")
    @ApiOperation(value="通过log查询余额", notes="通过log查询余额")
    fun onGetBalanceByLog(@RequestBody queryBalancePO : QueryBalancePO): BizResponse<BalanceVO> {
        val handler = CoinHandler.getHandler(queryBalancePO.symbol!!)
        val balance = handler!!.getBalanceByLog(queryBalancePO.address)
        val accountInfoVO = handler!!.getAccountInfo(queryBalancePO.address!!)
        balance!!.regId =  accountInfoVO!!.regID

        return BizResponse(balance)
    }
    val logger = LoggerFactory.getLogger(javaClass)
}