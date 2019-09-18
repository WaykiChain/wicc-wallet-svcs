package com.waykichain.chain.commons.biz.xservice

import com.waykichain.chain.biz.domain.BcWiccBlock
import com.waykichain.chain.po.*
import com.waykichain.chain.vo.*
import com.waykichain.coin.wicc.po.CreateContractPO
import com.waykichain.coin.wicc.vo.WiccInfoJsonRpcResponse
import com.waykichain.commons.base.BizResponse
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

    abstract fun scanBlock()

    abstract fun addNewBlock(blockId:Int)

    abstract fun getTransactions(queryTransactionPO: QueryTransactionPO): TransactionsVO

    abstract fun genAddress(addressType:Int?): CoinAddressVO

    abstract fun sendTransaction(coinSendTransactionPO : CoinSendTransactionPO): String?

    abstract fun getTxidInfo(queryTxidInfoPO: QueryTxidInfoPO): TxidVO?

    abstract fun getChainTxidInfo(txid:String): BizResponse<TxidDetailVO>

    abstract fun composed()

    abstract fun getBalance(address: String?): BizResponse<BalanceVO>

    abstract fun getBalanceByLog(address: String?): BalanceVO?

    abstract fun submitOfflineTransaction(coinOfflineTransactionPO: CoinOfflineTransactionPO): String?

    abstract fun sendContractTx(contractTransactionPO : ContractTransactionPO): String?

    abstract fun getAccountInfo(address:String): AccountInfoVO?

    abstract fun getAccountInfoWithError(address: String): BizResponse<AccountInfoVO>

    abstract fun getChainInfo(): WiccInfoJsonRpcResponse?

    abstract fun getChainTxidDetailInfo(txid: String) : BizResponse<TxidDetailInfoVO>

    abstract fun registerAccountTx(queryAccountInfoPO: QueryAccountInfoPO) : String?

    abstract fun createContract(createContractPO: CreateContractPO): String?

    abstract fun getBlockHash(blockId:Int): String?

    abstract fun getBlockCount(): Long?

    abstract fun getTotalCoin(): BizResponse<BigDecimal>

    abstract fun getBlockByHeight(height: Int): BcWiccBlock

    abstract fun checkRollback()

    abstract fun checkChainStop(): Boolean

}