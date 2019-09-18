package com.waykichain.chain.commons.biz.xservice.impl

import com.waykichain.chain.commons.biz.dict.ErrorCode
import com.waykichain.chain.commons.biz.service.BcWiccContractInfoService
import com.waykichain.chain.commons.biz.utils.WiccUtils
import com.waykichain.chain.commons.biz.xservice.WiccMethodClient
import com.waykichain.chain.contract.util.ContractUtil
import com.waykichain.chain.po.QueryContractData
import com.waykichain.chain.po.v2.ContractInfoPO
import com.waykichain.chain.vo.ContractDataVO
import com.waykichain.chain.vo.v2.ContractInfoVO
import com.waykichain.coin.wicc.po.*
import com.waykichain.coin.wicc.vo.WiccGetAppAccInfoJsonRpcResponse
import com.waykichain.coin.wicc.vo.WiccGetContractRegidJsonRpcResponse
import com.waykichain.coin.wicc.vo.WiccGetContractRegidResult
import com.waykichain.coin.wicc.vo.WiccSubmitTxJsonRpcResponse
import com.waykichain.commons.base.BizResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Created by Joss on 05/16/18.
 */

@Service("contractXservice")
open class ContractXservice  {


    fun getExchangeRatio(regId:String): BigDecimal? {


        var po = GetContractDataPO()
        po.setContractRegid(regId)
        var key = ContractUtil.toHexString("key_tokenrate")
        po.setKey(key);
        //     po.setKey("65786368616e676552617465");
        var response = wiccMethodClient.getClient().getScriptData(po)
        var result = response.getResult()

        var exchangeRate = ContractUtil.upsidedownHex(result.getValue()).toLong(16).
                toBigDecimal().divide(10000.toBigDecimal(), 8,RoundingMode.HALF_DOWN)
        System.out.println("exchangeRate：" + exchangeRate)

        return exchangeRate
    }

    fun testSendMoney(address:String): WiccSubmitTxJsonRpcResponse? {
        var  sendToAddressPO = SendTxPO()
        sendToAddressPO.amount = BigDecimal(100000000000)
        sendToAddressPO.sendAddress = "wNd7RL89zKpJ7BRxcLXZjyEdaFHcvkXaXn"
        sendToAddressPO.receiveAddress = address

        var response = wiccMethodClient.getClient().sendToAddress(sendToAddressPO)
        return response
    }

    fun testSendMoney10W(address:String): WiccSubmitTxJsonRpcResponse? {
        var  sendToAddressPO = SendTxPO()
        sendToAddressPO.amount = BigDecimal(10000000000000)
        sendToAddressPO.sendAddress = "wNd7RL89zKpJ7BRxcLXZjyEdaFHcvkXaXn"
        sendToAddressPO.receiveAddress = address

        var response = wiccMethodClient.getClient().sendToAddress(sendToAddressPO)
        return response
    }

    fun getTokenBalance(regId:String, address:String): WiccGetAppAccInfoJsonRpcResponse? {
        var getAppAccInfoPO = GetAppAccInfoPO()
        getAppAccInfoPO.address = address
        getAppAccInfoPO.scriptid = regId
        var response = wiccMethodClient.getClient().getappaccinfo(getAppAccInfoPO)
        response!!.result.tokenAmount = WiccUtils.convert(response!!.result.freeValues)
        return response
    }

    fun getContractRegid(txHash:String): WiccGetContractRegidJsonRpcResponse? {
        var getContractRegidPO = GetContractRegidPO()
        getContractRegidPO.hash = txHash
        var response = wiccMethodClient.getClient().getContractRegid(getContractRegidPO)
        return response
    }

    fun getContradtData(queryContractData: QueryContractData): ContractDataVO{
        var getContractDataPO = GetContractDataPO()
        getContractDataPO.contractRegid = queryContractData.contractRegid
        getContractDataPO.key = ContractUtil.toHexString(queryContractData.key)
        var response = wiccMethodClient.getClient().getScriptData(getContractDataPO)

        var contractDataVO = ContractDataVO()
        contractDataVO.contractRegid = queryContractData.contractRegid
        contractDataVO.key = queryContractData.key
        if(response.error == null){
            contractDataVO.value = response.result.value
        } else{
            contractDataVO.errorMsg = response.error.message
        }

        return contractDataVO
    }

    /**
     * 获取智能合约信息
     *
     * [RPC-getcontractinfo]
     */
     fun getContractInfo(po: ContractInfoPO):  BizResponse<ContractInfoVO> {

        var contractInfoPO = GetContractInfoPO()
        contractInfoPO.regid = po.regid
        val response = wiccMethodClient.getClient().getContractInfo(contractInfoPO)
        var bizResponse = BizResponse<ContractInfoVO>()
        if (response.result != null) {
            var vo = ContractInfoVO()
            vo.contractregid = response.result.contract_regid
            vo.contractmemo = response.result.memo
            vo.contractcontent = ContractUtil.ConvertContractData(response.result.code)
            bizResponse.data = vo
        } else {
            if (response.error != null)  {
                bizResponse.code = response.error.code
                bizResponse.msg = response.error.message
            } else {
                logger.error("getContractInfo() error,response=$response")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }

        return bizResponse
    }
/*

        override fun getBalance(address: String?): BalanceVO? {
            val accountInfo = wiccMethodClient.getClient().getAccountInfo(address)
            var balanceVO = BalanceVO()
            balanceVO.balance = accountInfo.result.balance
            balanceVO.number = accountInfo.result.updateHeight
            return balanceVO
        }
    }*/
/*
    fun getTokenBalance(address: String): BigDecimal? {

            val accountInfo = wiccMethodClient.getClient().getAccountInfo(address)
            var balanceVO = BalanceVO()
            balanceVO.balance = accountInfo.result.balance
            balanceVO.number = accountInfo.result.updateHeight
            return balanceVO
    }*/


    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired lateinit var wiccMethodClient: WiccMethodClient
    @Autowired lateinit var bcWiccContractInfoService: BcWiccContractInfoService

}