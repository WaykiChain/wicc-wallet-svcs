package com.waykichain.chain.commons.biz.xservice

import com.waykichain.chain.po.*
import com.waykichain.chain.vo.*
import com.waykichain.coin.wicc.vo.WiccInfoJsonRpcResponse

/**
 * Created by Joss on 05/16/18.
 */
abstract class CoinHandler {

    companion object {

        private val coinHandlerMap: MutableMap<String, CoinHandler> = mutableMapOf()

        fun regHandler(symbol: String, handler: CoinHandler) {
            coinHandlerMap[symbol] = handler
        }

        fun getHandler(symbol: String) = coinHandlerMap[symbol]
    }

    abstract fun scanBlock()

    abstract fun addNewBlock(blockId:Int)

    abstract fun getTransactions(queryTransactionPO: QueryTransactionPO): TransactionsVO

    abstract fun genAddress(addressType:Int?): CoinAddressVO

    abstract fun sendTransaction(coinSendTransactionPO : CoinSendTransactionPO): String?

    abstract fun getTxidInfo(queryTxidInfoPO: QueryTxidInfoPO): TxidVO?

    abstract fun getChainTxidInfo(txid:String): TxidDetailVO


    abstract fun getBalance(address: String?): BalanceVO?

    abstract fun getBalanceByLog(address: String?): BalanceVO?

    abstract fun submitOfflineTransaction(coinOfflineTransactionPO: CoinOfflineTransactionPO): String?

    abstract fun sendContractTx(contractTransactionPO : ContractTransactionPO): String?

    abstract fun getAccountInfo(address:String): AccountInfoVO?


    abstract fun getChainInfo(): WiccInfoJsonRpcResponse?

}