package com.waykichain.chain.webapi.controller

import com.waykichain.chain.commons.biz.dict.CoinType
import com.waykichain.chain.commons.biz.xservice.CoinHandler
import com.waykichain.chain.commons.biz.xservice.impl.ContractXservice
import com.waykichain.chain.po.ContractTransactionPO
import com.waykichain.chain.po.QueryContractData
import com.waykichain.chain.po.v2.ContractInfoPO
import com.waykichain.chain.vo.ContractDataVO
import com.waykichain.chain.vo.v2.ContractInfoVO
import com.waykichain.coin.wicc.po.CreateContractPO
import com.waykichain.coin.wicc.po.GetContractRegidPO
import com.waykichain.coin.wicc.vo.WiccGetAppAccInfoJsonRpcResponse
import com.waykichain.coin.wicc.vo.WiccGetContractRegidJsonRpcResponse
import com.waykichain.coin.wicc.vo.WiccGetContractRegidResult
import com.waykichain.coin.wicc.vo.WiccSubmitTxJsonRpcResponse
import com.waykichain.commons.base.BizResponse
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import javax.validation.Valid

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

    @PostMapping("/create")
    @ApiOperation("发起合约交易")
    fun createContract(@RequestBody createContractPO: CreateContractPO) : BizResponse<String> {

        val handler = CoinHandler.getHandler(CoinType.WICC.symbol)
        val txid = handler!!.createContract(createContractPO)
        return BizResponse(txid)
    }


    @GetMapping("/exchange/rate/{regId}")
    @ApiOperation(value="获取汇率", notes="获取汇率")
    fun getExchangeRate(@PathVariable regId:String): BizResponse<BigDecimal> {
        return BizResponse(contacContractXservicet.getExchangeRatio(regId))
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
    fun onGetWusdBalance(@PathVariable("contractRegId") regId:String, @PathVariable address:String): BizResponse<WiccGetAppAccInfoJsonRpcResponse> {
        return BizResponse(contacContractXservicet.getTokenBalance(regId, address))
    }

    @PostMapping("/regid")
    @ApiOperation(value="Get Contract Regid", notes="Get Contract Regid , it will be deleted")
    fun getContractRegId_Old(@RequestBody getContractRegidPO: GetContractRegidPO):BizResponse<WiccGetContractRegidJsonRpcResponse> {
        return BizResponse(contacContractXservicet.getContractRegid(getContractRegidPO.hash))
    }

    @PostMapping("/getcontractregid")
    @ApiOperation(value="Get Contract Regid", notes="Get Contract Regid ")
    fun getContractRegId(@RequestBody getContractRegidPO: GetContractRegidPO):BizResponse<WiccGetContractRegidJsonRpcResponse> {
        return BizResponse(contacContractXservicet.getContractRegid(getContractRegidPO.hash))
    }

    @PostMapping("getcontractdata")
    @ApiOperation(value="Get Contract Data", notes="Get Contract Data with Data Type (STRING,NUMBER,HEX)")
    fun getContractData(@RequestBody queryContractData:QueryContractData):BizResponse<ContractDataVO>{
        return BizResponse(contacContractXservicet.getContradtData(queryContractData))
    }


    @PostMapping("/getcontractinfo")
    @ApiOperation(value = "Get Contract Info", notes = "Get Contract Info[RPC-getcontractinfo]",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun  getContractInfo(@RequestBody @Valid po: ContractInfoPO):  BizResponse<ContractInfoVO> {
        return contacContractXservicet.getContractInfo(po)
    }

    @Autowired lateinit var contacContractXservicet: ContractXservice

}