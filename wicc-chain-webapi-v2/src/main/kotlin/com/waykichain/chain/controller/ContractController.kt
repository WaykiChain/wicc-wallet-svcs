package com.waykichain.chain.controller

import com.waykichain.chain.commons.biz.xservice.ContractXService
import com.waykichain.chain.po.v2.*
import com.waykichain.chain.vo.v2.*
import com.waykichain.commons.base.BizResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import javax.validation.Valid

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 15:05
 *
 * @Description:    区块相关
 *
 */
@RestController
@RequestMapping("/contract")
@Api(description = "contract|智能合约相关")
class ContractController {

    @PostMapping("callcontracttx")
    @ApiOperation(value = "【Call smart contract】【调用智能合约】",
            notes = "Please ensure that there is a corresponding private key in the Baas wallet node  \n" +
            "请确保Baas钱包节点中存在合约调用者私钥",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun callContracttx(@RequestBody @Valid po: CallContracttxPO): BizResponse<CallContracttxVO> {
        return contractXService.callContracttx(po)
    }

/*
    @PostMapping("sendContractTx")
    @ApiOperation(value = "（管理员）创建调用智能合约交易", notes = "（管理员）创建调用智能合约交易", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun sendContractTx(@RequestBody @Valid po : SendContractPO): BizResponse<CallContracttxVO> {
        return contractXService.sendContractTx(po)
    }
*/

    @PostMapping("getcontractdata")
    @ApiOperation(value = "【Get the key-value value in a smart contract】【获取智能合约中key-value值】",
            notes = "",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun getContradtData(@RequestBody @Valid po: ContractdataPO):  BizResponse<ContractDataDetailVO> {
        return contractXService.getContradtData(po)
    }

    @PostMapping("getcontractregid")
    @ApiOperation(value = "【Get the regid of the smart contract】【获取智能合约的regid】",
            notes = "",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun getContractRegid(@RequestBody @Valid po: ContractRegidPO):  BizResponse<ContractRegidVO> {
        return contractXService.getContractRegid(po.txhash!!)
    }

//    @PostMapping("exchangeRate")
//    @ApiOperation(value = "获取汇率", notes = "获取汇率", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//    fun  getExchangeRate(@RequestBody @Valid po: ExchangeRatePO):  BizResponse<BigDecimal> {
//        return contractXService.getExchangeRate(po.regid!!)
//    }

    @PostMapping("getcontractinfo")
    @ApiOperation(value = "【Get details of smart contracts】【获取智能合约的详细信息】",
            notes = "",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun  getContractInfo(@RequestBody @Valid po: ContractInfoPO):  BizResponse<ContractInfoVO> {
        return contractXService.getContractInfo(po)
    }


    @PostMapping("getcontractaccountinfo")
    @ApiOperation(value = "【Get information about account in smart contract】【获取账户在智能合约中的相关信息】",
            notes = "",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun  getContractAccountInfo(@RequestBody @Valid po: ContractAccountInfoPO):  BizResponse<ContractAccountInfoVO> {
        return contractXService.getContractAccountInfo(po)
    }

    @Autowired
    lateinit var contractXService: ContractXService
}