package com.waykichain.chain.controller

import com.waykichain.chain.commons.biz.dict.TransactionConstantDict
import com.waykichain.chain.commons.biz.xservice.TransactionXService
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
import javax.validation.Valid

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/4 19:48
 *
 * @Description:    交易相关
 *
 */

@RestController
@RequestMapping("/transaction")
@Api(description = "transaction|交易相关")
class TransactionController {

//    @PostMapping("/uuid")
//    @ApiOperation(value="更新UUID", notes="更新UUID", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//    fun updateUUID(@RequestBody @Valid po : UpdateUuidPO): BizResponse<Any> {
//        return transactionXservice.updateUUID(po)
//    }

    @PostMapping("/gettranscationsbyaddress")
    @ApiOperation(value = "【Get transaction records based on address】【根据地址查询交易记录】",
            notes = "",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun find(@RequestBody @Valid po: TransactionQueryPO): BizResponse<WiccTransactionsVO> {
        return transactionXservice.getTransactions(po)
    }

//    @PostMapping("/detail")
//    @ApiOperation(value = "根据交易哈希获取交易记录（从数据表获取）", notes = "根据交易哈希获取交易记录（从数据表获取）",
//            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//    fun tranDetail(@RequestBody @Valid po: WiccTransactionDetailPO): BizResponse<WiccTransactionVO> {
//        return transactionXservice.getTranDetailByTX(po)
//    }

    @PostMapping("/gettxdetail")
    @ApiOperation(value = "【Get transaction details based on transaction hash】【根据交易哈希获取交易详情】",
            notes = "",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun getTxDetail(@RequestBody @Valid po: WiccTransactionDetailPO): BizResponse<WiccTransactionVO> {
        return transactionXservice.getTranDetailFromRPC(po.hash!!)
    }

    @PostMapping("/sendtoaddress")
    @ApiOperation(value = "【Common transfer 】【普通转账】",
            notes = "Please ensure that there is a corresponding private key in the Baas wallet node  \n" +
                    "请确保Baas钱包节点中存在发送者私钥",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun sendToAddress(@RequestBody @Valid po: SendtoaddressPO): BizResponse<SendtoaddressVO> {
        return transactionXservice.sendtoaddress(po)
    }

    @PostMapping("/sendtoaddresswithfee")
    @ApiOperation(value = "【Common transfer with fee】【手动设置手续费进行普通转账】",
            notes = "Please ensure that there is a corresponding private key in the Baas wallet node  \n" +
                    "请确保Baas钱包节点中存在发送者私钥",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun sendToAddressWithFee(@RequestBody @Valid po: SendtoAddressWithFeePO): BizResponse<SendtoaddressVO> {
        return transactionXservice.sendtoaddressWithFee(po)
    }

    @PostMapping("/gensendtoaddressraw")
    @ApiOperation(value = "【Generate a transfer transaction signature】【创建转账交易签名】",
            notes = "Please ensure that there is a corresponding private key in the Baas wallet node  \n" +
                    "请确保Baas钱包节点中存在发送者私钥",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun genSendtoAddresstxraw(@RequestBody @Valid po: GenSendToAddressTxrawPO): BizResponse<GenSendToAddressTxrawVO> {
        return transactionXservice.genSendtoAddresstxraw(po)
    }

    @PostMapping("/listtx")
    @ApiOperation(value = "【Get the list of transactions in the remote wallet node】【获取远程钱包节点中的交易列表】",
            notes = "",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun listTx(): BizResponse<ListTxVO> {
        return transactionXservice.listTx()
    }

    @PostMapping("/decoderawtx")
    @ApiOperation(value = "【Get the original transaction detail based on the signature data】【根据签名数据获取原始交易单】",
            notes = "",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun decodeRawtx(@RequestBody @Valid po: DecodeRawtxPO): BizResponse<DecodeRawtxVO> {
        return transactionXservice.decodeRawtx(po)
    }

    @PostMapping("/sendrawtx")
    @ApiOperation(value = "【Broadcast transaction signature data to blockchain】【将交易签名数据广播至区块链】",
            notes = "",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun offlinTransaction(@RequestBody @Valid po: OfflineTransactionPO): BizResponse<WiccTransactionVO> {
        return transactionXservice.submitOfflineTransaction(po)
    }

    @PostMapping("/getcoinsfromfaucet")
    @ApiOperation(value = "【Get 10 test coins from Testnet】【从测试网获取10个WICC测试币】",
            notes = "",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun depositTestMoneyK(@RequestBody @Valid po: DepositTestMoneyPO): BizResponse<SendtoaddressVO> {
        return transactionXservice.depositTestMoney(po.recviver!!, TransactionConstantDict.TEST_MONEY_10.value)
    }

//   @PostMapping("/depositTestMoneyW")
//    @ApiOperation(value = "添加测试金额（10000wicc）", notes = "添加测试金额（10000wicc）", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//    fun depositTestMoneyW(@RequestBody @Valid po: DepositTestMoneyPO): BizResponse<SendtoaddressVO> {
//        return transactionXservice.depositTestMoney(po.recviver!!, TransactionConstantDict.TEST_MONEY_10000.value)
//    }



    @Autowired
    lateinit var transactionXservice: TransactionXService

}