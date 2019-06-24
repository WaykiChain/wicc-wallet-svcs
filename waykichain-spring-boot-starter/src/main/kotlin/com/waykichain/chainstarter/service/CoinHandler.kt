package com.waykichain.chainstarter.service

import com.waykichain.chain.po.*
import com.waykichain.chain.vo.*
import com.waykichain.coin.wicc.po.CreateContractPO
import com.waykichain.coin.wicc.vo.WiccBlockResult
import com.waykichain.coin.wicc.vo.WiccInfoJsonRpcResponse
import java.math.BigDecimal

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


    abstract fun getCoinSymbol(): String

    abstract fun genAddress(addressType:Int?): CoinAddressVO

    abstract fun sendTransaction(coinSendTransactionPO : CoinSendTransactionPO): String?

    abstract fun getTxidInfo(queryTxidInfoPO: QueryTxidInfoPO): TxidVO?

    abstract fun getChainTxidInfo(txid:String): TxidDetailVO

    abstract fun getBalance(address: String?): BalanceVO?

    abstract fun submitOfflineTransaction(coinOfflineTransactionPO: CoinOfflineTransactionPO): String?

    abstract fun getAccountInfo(address:String): AccountInfoVO?

    abstract fun getChainInfo(): WiccInfoJsonRpcResponse?

    abstract fun getChainTxidDetailInfo(txid: String) : TxidDetailInfoVO?

    abstract fun registerAccountTx(queryAccountInfoPO: QueryAccountInfoPO) : String?

    abstract fun createContract(createContractPO: CreateContractPO): String?

    abstract fun getBlockHash(blockId:Int): String?

    abstract fun getBlockCount(): Long?

    abstract fun getTotalCoin(): BigDecimal?

    abstract fun getBlockByHeight(height: Int): WiccBlockResult?

}