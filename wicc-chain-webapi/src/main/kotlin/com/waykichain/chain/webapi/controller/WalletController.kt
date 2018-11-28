package com.waykichain.chain.webapi.controller

import com.waykichain.chain.commons.biz.domain.BizResponse
import com.waykichain.chain.commons.biz.xservice.CoinHandler
import com.waykichain.chain.po.*
import com.waykichain.chain.vo.*
import com.waykichain.coin.wicc.vo.WiccInfoResult
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chain")
class WalletController {
    @PostMapping("/account")
    @ApiOperation(value="获取AccountInfo", notes="获取AccountInfo")
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
    @ApiOperation(value="根据地址查找是否有新的交易", notes="根据地址查找是否有新的交易")
    fun find(@RequestBody queryTransactionPO : QueryTransactionPO): BizResponse<TransactionsVO> {
        val handler = CoinHandler.getHandler(queryTransactionPO.symbol!!)
        return BizResponse(handler!!.getTransactions(queryTransactionPO))
    }

    @PostMapping("/submit/offtrans")
    @ApiOperation(value="提交离线交易", notes="提交离线交易")
    fun onSubmitOfflineTransaction(@RequestBody coinOfflineTransactionPO : CoinOfflineTransactionPO): BizResponse<TxidDetailVO> {
        val handler = CoinHandler.getHandler(coinOfflineTransactionPO.symbol!!)
        val txid = handler!!.submitOfflineTransaction(coinOfflineTransactionPO)
        val txidDetailVO = handler!!.getChainTxidInfo(txid!!)
        return BizResponse(txidDetailVO)

    }

    @PostMapping("/address/gen")
    @ApiOperation(value="查找记录是否有新的交易", notes="查找记录是否有新的交易")
    fun gen(@RequestBody coinAddressGenPO : CoinAddressGenPO): BizResponse<CoinAddressVO> {
        val handler = CoinHandler.getHandler(coinAddressGenPO.symbol!!)
        val coinAddressVO = handler!!.genAddress(coinAddressGenPO.walletAddressType)
        return BizResponse(coinAddressVO)
    }

    @PostMapping("/transaction")
    @ApiOperation(value="发送交易", notes="发送交易")
    fun onTransaction(@RequestBody coinSendTransactionPO : CoinSendTransactionPO): BizResponse<CoinSendTransactionVO> {
        val handler = CoinHandler.getHandler(coinSendTransactionPO.symbol!!)
        val txid = handler!!.sendTransaction(coinSendTransactionPO)
        var coinSendTransactionVO = CoinSendTransactionVO()
        coinSendTransactionVO.txid = txid
        return BizResponse(coinSendTransactionVO)
    }

    @PostMapping("/transaction/find")
    @ApiOperation(value="查找记录是否有新的交易", notes="查找记录是否有新的交易")
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

    val logger = LoggerFactory.getLogger(javaClass)
}