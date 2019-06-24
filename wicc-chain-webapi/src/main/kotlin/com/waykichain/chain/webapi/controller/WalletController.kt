package com.waykichain.chain.webapi.controller

import com.waykichain.chain.commons.biz.xservice.BcWiccSendTransactionLogXService
import com.waykichain.chain.commons.biz.xservice.CoinHandler
import com.waykichain.chain.po.*
import com.waykichain.chain.vo.*
import com.waykichain.coin.wicc.vo.WiccInfoResult
import com.waykichain.commons.base.BizResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import sun.awt.Symbol
import java.math.BigDecimal

@RestController
@RequestMapping("/chain")
@Api(value = "[this controller is always for wallet info. Especially: the field 【symbol】is always 【WICC】if there is no special declaration]")
class WalletController {


    @PostMapping("/update/uuid")
    @ApiOperation(value="更新uuid", notes="更新uuid")
    fun updateUuid(@RequestBody coinSendTransactionPO : CoinSendTransactionPO): BizResponse<Any> {

        bcWiccSendTransactionLogXService.updateBcWiccSendTransactionLogRequestUuid(coinSendTransactionPO.requestUUID!!)
        return BizResponse(null)
    }

    @PostMapping("/account")
    @ApiOperation(value="Get the account info on the given address  [获取AccountInfo]", notes="[获取AccountInfo]")
    fun getAccountInfo(@RequestBody queryAccountInfoPO : QueryAccountInfoPO): BizResponse<AccountInfoVO> {

        val handler = CoinHandler.getHandler(com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol)
        val accountInfoVO = handler!!.getAccountInfo(queryAccountInfoPO.address!!)

        return BizResponse(accountInfoVO)
    }

    @GetMapping("/account/{address}")
    @ApiOperation(value="获取AccountInfo", notes="获取AccountInfo")
    fun getAccountInfoByAddress(@PathVariable address:String): BizResponse<AccountInfoVO> {

        val handler = CoinHandler.getHandler(com.waykichain.chain.commons.biz.dict.CoinType.WICC.symbol)
        val accountInfoVO = handler!!.getAccountInfo(address)

        return BizResponse(accountInfoVO)
    }

    @PostMapping("/find")
    @ApiOperation(
            value="Get all transaction info according to the given address and start blockHeight, \n [根据交易地址查询所有的交易信息]",
            notes="Get all transaction info according to the given address and start blockHeight, \n [根据交易地址和查询所有交易信息]")
    fun find(@RequestBody queryTransactionPO : QueryTransactionPO): BizResponse<TransactionsVO> {
        val handler = CoinHandler.getHandler(queryTransactionPO.symbol!!)
        return BizResponse(handler!!.getTransactions(queryTransactionPO))
    }

    @PostMapping("/submit/offtrans")
    @ApiOperation(value="submit offline transactions【提交离线交易】", notes="submit offline transactions【提交离线交易】")
    fun onSubmitOfflineTransaction(@RequestBody coinOfflineTransactionPO : CoinOfflineTransactionPO): BizResponse<TxidDetailVO> {
        val handler = CoinHandler.getHandler(coinOfflineTransactionPO.symbol!!)
        val txid = handler!!.submitOfflineTransaction(coinOfflineTransactionPO)
        val txidDetailVO = handler!!.getChainTxidInfo(txid!!)
        return BizResponse(txidDetailVO)

    }

    @PostMapping("/address/gen")
    @ApiOperation(value= "Generator new address [创建新地址]", notes="Generator new address [创建新地址]")
    fun gen(@RequestBody coinAddressGenPO : CoinAddressGenPO): BizResponse<CoinAddressVO> {
        val handler = CoinHandler.getHandler(coinAddressGenPO.symbol!!)
        val coinAddressVO = handler!!.genAddress(coinAddressGenPO.walletAddressType)
        return BizResponse(coinAddressVO)
    }

    @PostMapping("/transaction")
    @ApiOperation(value="Transfer coin from the source address to the destination address,10000 sawi fee default, 【发送交易】",
            notes="Transfer coin from the source address to the destination address,10000 sawi fee default， 【发送交易】")
    fun onTransaction(@RequestBody coinSendTransactionPO : CoinSendTransactionPO): BizResponse<CoinSendTransactionVO> {
        val handler = CoinHandler.getHandler(coinSendTransactionPO.symbol!!)
        val txid = handler!!.sendTransaction(coinSendTransactionPO)
        var coinSendTransactionVO = CoinSendTransactionVO()
        coinSendTransactionVO.txid = txid
        return BizResponse(coinSendTransactionVO)
    }

    @PostMapping("/transaction/find")
    @ApiOperation(value="Find the transaction detail according to the given txHash[txid] 【根据交易hash获取交易详情】",
            notes="Find the transaction detail according to the given txHash[txid] 【根据交易hash获取交易详情】")
    fun onFindTransaction(@RequestBody queryTxidInfoPO : QueryTxidInfoPO): BizResponse<TxidVO> {
        val handler = CoinHandler.getHandler(queryTxidInfoPO.symbol!!)
        val txidVO = handler!!.getTxidInfo(queryTxidInfoPO)
        return BizResponse(txidVO)
    }


    @PostMapping("/balance")
    @ApiOperation(value="获取链账户余额", notes="获取链账户余额")
    fun onGetBalance(@RequestBody queryBalancePO : QueryBalancePO): BizResponse<BalanceVO> {
        val handler = CoinHandler.getHandler(queryBalancePO.symbol!!)
        val balance = handler!!.getBalance(queryBalancePO.address)
        return BizResponse(balance)
    }

    @PostMapping("/balanceByLog")
    @ApiOperation(value="通过log查询余额", notes="通过log查询余额")
    fun onGetBalanceByLog(@RequestBody queryBalancePO : QueryBalancePO): BizResponse<BalanceVO> {
        val handler = CoinHandler.getHandler(queryBalancePO.symbol!!)
        val balance = handler!!.getBalanceByLog(queryBalancePO.address)
        val accountInfoVO = handler!!.getAccountInfo(queryBalancePO.address!!)
        balance!!.regId =  accountInfoVO!!.regID

        return BizResponse(balance)
    }


    @PostMapping("/{coinSymbol}/getblockinfo")
    @ApiOperation(value="获取区块信息", notes="获取区块信息")
    fun getBlockInfo(@PathVariable coinSymbol:String): BizResponse<WiccInfoResult> {
        val handler = CoinHandler.getHandler(coinSymbol)
        val reponse = handler!!.getChainInfo()
        return BizResponse(reponse!!.result)
    }

    @GetMapping("/{coinSymbol}/getblockcount")
    @ApiOperation(value = "Get the Block height【获取区块高度】", notes = "Get the Block height【获取区块高度】")
    fun getBlockCount(@PathVariable("coinSymbol") coinSymbol: String) : BizResponse<Long?> {

        val handler = CoinHandler.getHandler(coinSymbol)
        val response = handler!!.getBlockCount()
        return BizResponse(response)
    }

    @PostMapping(value = ["/transaction/find/detail"], produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ApiOperation(value = "根据交易hash获取交易详细信息", notes = "根据交易hash获取交易详细信息")
    fun getTransactionDetailInfoByTxid(@RequestBody queryTxidInfoPO: QueryTxidInfoPO): BizResponse<TxidDetailInfoVO> {
        val handler = CoinHandler.getHandler(queryTxidInfoPO.symbol!!)
        val txidDetailInfoVO = handler!!.getChainTxidDetailInfo(queryTxidInfoPO.txid!!)
        return BizResponse(txidDetailInfoVO)
    }

    /**
     * 钱包激活
     */
    @PostMapping(value = ["/account/register"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    @ApiOperation(value = "钱包激活", notes = "钱包激活")
    fun registerAccountTx(@RequestBody queryAccountInfoPO: QueryAccountInfoPO) :BizResponse<String?> {

        val handler = CoinHandler.getHandler(queryAccountInfoPO.symbol!!)
        return BizResponse(handler!!.registerAccountTx(queryAccountInfoPO))
    }


    @PostMapping("/coin/total")
    @ApiOperation(value = "获取所有的WICC数量", notes = "获取所有的WICC数量")
    fun getTotalCoin(@RequestBody queryTotalCoinPO: QueryTotalCoinPO): BizResponse<BigDecimal> {
        val handler = CoinHandler.getHandler(queryTotalCoinPO.symbol!!)
        return BizResponse(handler!!.getTotalCoin())
    }

    val logger = LoggerFactory.getLogger(javaClass)
    @Autowired lateinit var bcWiccSendTransactionLogXService: BcWiccSendTransactionLogXService
}