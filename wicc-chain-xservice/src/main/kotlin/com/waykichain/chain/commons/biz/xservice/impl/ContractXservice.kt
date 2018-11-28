package com.waykichain.chain.commons.biz.xservice.impl

import com.waykichain.chain.commons.biz.service.BcWiccContractInfoService
import com.waykichain.chain.commons.biz.utils.WiccUtils
import com.waykichain.chain.commons.biz.xservice.WiccMethodClient
import com.waykichain.chain.contract.util.ContractUtil
import com.waykichain.coin.wicc.po.GetAppAccInfoPO
import com.waykichain.coin.wicc.po.GetScriptDataPO
import com.waykichain.coin.wicc.po.SendToAddressPO
import com.waykichain.coin.wicc.vo.WiccGetAppAccInfoJsonRpcResponse
import com.waykichain.coin.wicc.vo.WiccSubmitTxJsonRpcResponse
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


    fun getExchangeRatio(id:Long): BigDecimal? {

        val bcWiccContractInfo = bcWiccContractInfoService.getById(id)

        var po = GetScriptDataPO()
        po.setScriptid(bcWiccContractInfo!!.contractAddressRegId!!)
        var key = ContractUtil.toHexString("exchangeRate")
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
        var  sendToAddressPO = SendToAddressPO()
        sendToAddressPO.amount = BigDecimal(100000000000)
        sendToAddressPO.sendAddress = "wXzvgD3YwMccp5AVZDavLXkLFKMcmBChAx"
        sendToAddressPO.recvAddress = address

        var response = wiccMethodClient.getClient().sendToAddress(sendToAddressPO)
        return response
    }

    fun testSendMoney10W(address:String): WiccSubmitTxJsonRpcResponse? {
        var  sendToAddressPO = SendToAddressPO()
        sendToAddressPO.amount = BigDecimal(10000000000000)
        sendToAddressPO.sendAddress = "wXzvgD3YwMccp5AVZDavLXkLFKMcmBChAx"
        sendToAddressPO.recvAddress = address

        var response = wiccMethodClient.getClient().sendToAddress(sendToAddressPO)
        return response
    }

    fun getTokenBalance(regId:String, address:String): WiccGetAppAccInfoJsonRpcResponse?
    {
        var getAppAccInfoPO = GetAppAccInfoPO()
        getAppAccInfoPO.address = address
        getAppAccInfoPO.scriptid = regId
        var response = wiccMethodClient.getClient().getappaccinfo(getAppAccInfoPO)
        response!!.result.tokenAmount = WiccUtils.convert(response!!.result.freeValues)
        return response
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