package com.waykichain.chain.webapi.controller

import com.waykichain.chain.commons.biz.domain.BizResponse
import com.waykichain.chain.commons.biz.xservice.CoinHandler
import com.waykichain.chain.commons.biz.xservice.impl.ContractXservice
import com.waykichain.chain.po.ContractTransactionPO
import com.waykichain.coin.wicc.vo.WiccGetAppAccInfoJsonRpcResponse
import com.waykichain.coin.wicc.vo.WiccSubmitTxJsonRpcResponse
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/contract")
class ContractController {

    @PostMapping("/send")
    @ApiOperation(value="发起合约交易", notes="发起合约交易")
    fun onTransaction(@RequestBody contractTransactionPO : ContractTransactionPO): BizResponse<String> {

        val handler = CoinHandler.getHandler(com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol)
        val txid = handler!!.sendContractTx(contractTransactionPO)
        return BizResponse(txid)
    }

    @GetMapping("/exchange/rate/{id}")
    @ApiOperation(value="获取汇率", notes="获取汇率")
    fun getExchangeRate(@PathVariable id:Long): BizResponse<BigDecimal> {
        return BizResponse(contacContractXservicet.getExchangeRatio(id))
    }

    @GetMapping("/test/deposit/{address}")
    @ApiOperation(value="添加测试金额1000WICC", notes="添加测试金额100WICC")
    fun onAddTestMoney(@PathVariable address:String): BizResponse<WiccSubmitTxJsonRpcResponse> {
        return BizResponse(contacContractXservicet.testSendMoney(address))
    }

    @GetMapping("/test/deposit10w/{address}")
    @ApiOperation(value="添加测试金额1000WICC", notes="添加测试金额100WICC")
    fun onAddTestMoney10W(@PathVariable address:String): BizResponse<WiccSubmitTxJsonRpcResponse> {
        return BizResponse(contacContractXservicet.testSendMoney10W(address))
    }

    @GetMapping("/get/{contractRegId}/{address}")
    @ApiOperation(value="获取WUSD余额", notes="获取WUSD余额")
    fun onGetWusdBalance(@PathVariable regId:String, @PathVariable address:String): BizResponse<WiccGetAppAccInfoJsonRpcResponse> {
        return BizResponse(contacContractXservicet.getTokenBalance(regId, address))
    }

    @Autowired lateinit var contacContractXservicet: ContractXservice

}