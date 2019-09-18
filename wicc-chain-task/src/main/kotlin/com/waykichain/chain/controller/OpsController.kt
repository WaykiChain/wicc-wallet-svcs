package com.waykichain.chain.controller

import com.waykichain.chain.commons.biz.xservice.CoinHandler
import com.waykichain.chain.commons.biz.xservice.DingTalkService
import com.waykichain.chain.task.notify.ChainStopSynchronizeNotifyHandler
import com.waykichain.chain.task.notify.LargeTransactionNotifyHandler
import com.waykichain.chain.task.notify.LargeBalanceNotifyHandler
import com.waykichain.chain.task.notify.RollbackNotifyHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ops")
class OpsController {

    @GetMapping("/scan/{symbol}/{blockId}")
    fun onEthScan(@PathVariable symbol:String, @PathVariable blockId:Int): String? {
        val handler = CoinHandler.getHandler(symbol)
        handler!!.addNewBlock(blockId)
        return "SUCCESS"
    }

    @GetMapping("/{symbol}/scan")
    fun onCoinScan(@PathVariable symbol:String): String? {
        val handler = CoinHandler.getHandler(symbol)
        handler!!.scanBlock()
        return "SUCCESS"
    }


    @GetMapping("/test/send/{txt}")
    fun onTestSend(@PathVariable txt:String): String? {
        var url = "https://oapi.dingtalk.com/robot/send?access_token=c517b5c8db1883e3ec3833fa4aff78d4ded35aa3b31439cb7c5de111560c8150"
        dingTalkService.sendTextMessage(url, txt)
        return "SUCCESS"
    }

    @GetMapping("/trans/scan")
    fun onScan(): String? {
        largeTransactionNotifyHandler.scanTransaction()
        return "Success"
    }


    @GetMapping("/balance/scan")
    fun onBalanceScan(): String? {
        largeBalanceNotifyHandler.scanTransaction()
        return "Success"
    }

    @GetMapping("/rollback/scan")
    fun onRollbackScan():String?{
        println("-----------------on Rollbacn Scan------------------")
        rollbackNotifyHandler.checkRollback()
        return "Rollback scan Success"
    }

    @GetMapping("/check/chainstop")
    fun chainStopCheck():String?{
        println("-----------------on Rollbacn Scan------------------")
        chainStopSynchronizeNotifyHandler.checkChainStop()
        return "Check chain stop"
    }

    @Autowired lateinit var dingTalkService:DingTalkService
    @Autowired lateinit var largeTransactionNotifyHandler: LargeTransactionNotifyHandler
    @Autowired lateinit var largeBalanceNotifyHandler: LargeBalanceNotifyHandler
    @Autowired lateinit var rollbackNotifyHandler:RollbackNotifyHandler
    @Autowired lateinit var chainStopSynchronizeNotifyHandler:ChainStopSynchronizeNotifyHandler
    private val logger = LoggerFactory.getLogger(javaClass)
}