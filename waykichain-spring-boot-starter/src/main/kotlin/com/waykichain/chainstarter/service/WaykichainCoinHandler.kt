package com.waykichain.chainstarter.service

import com.waykichain.chainstarter.client.WiccMethodClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
open class WaykichainCoinHandler : CoinHandler() {

     init {
//         regHandler(getCoinSymbol(), this)
     }

//    override final fun getCoinSymbol(): String {
//        return "WICC"
//    }
//
//    override fun getAccountInfo(address: String): WiccAccountInfoResult? {
//        var accountInfoResponse = client().getAccountInfo(address)
//        validate(accountInfoResponse)
//        return accountInfoResponse.result
//
//    }
//
//    override fun getTxDetailJson(txHash: String): String? {
//        return client().getTxDetailJson(txHash)
//    }
//
//    override fun getTxDetail(txHash: String): BaseTx? {
//        val txJsonString = getTxDetailJson(txHash)?: return null
//        val txObject = JSONObject.parseObject(txJsonString)
//
//        val txType = txObject.getString("tx_type")?: return null
//        return transferTx(txJsonString, TransactionType.parseTransactionTypeToTxClass(txType))
//
//        return null
//    }
//
//    override fun getBalance(address: String?): BalanceVO? {
//        val accountInfo = client().getAccountInfo(address)
//        validate(accountInfo)
//
//        var balanceVO = BalanceVO()
//        balanceVO.balance = accountInfo.result.balance
//        balanceVO.number = accountInfo.result.updateHeight
//        return balanceVO
//    }
//
//    override fun getTxidInfo(queryTxidInfoPO: QueryTxidInfoPO): TxidVO? {
//
//        val resp = client().getTxDetail(queryTxidInfoPO.txid)
//
//        validate(resp)
//
//        var txidVO = TxidVO()
//        txidVO.symbol = getCoinSymbol()
//        txidVO.txid = queryTxidInfoPO.txid
//        txidVO.number = resp.result.confirmedheight
//        return txidVO
//    }
//
//    override fun getChainTxidInfo(txid: String): TxidDetailVO {
//        var response= client().getTxDetail(txid)
//
//        validate(response)
//
//        var txidDetailVO = TxidDetailVO()
//        txidDetailVO.sendAddress = response.result.addr
//        txidDetailVO.fee = response.result.fees.toBigDecimal()
//        if(response.result.money == null) {
//            txidDetailVO.amount = BigDecimal.ZERO
//        } else {
//            txidDetailVO.amount = BigDecimal(response.result.money)
//        }
//        txidDetailVO.toAddress = response.result.desaddr
//        txidDetailVO.regId = response.result.regid
//        txidDetailVO.txid = response.result.hash
//        txidDetailVO.contract = response.result.arguments
//        if(txidDetailVO.contract == null)
//            txidDetailVO.contract =response.result.arguments
//
//        txidDetailVO.arguments = response.result.arguments
//        txidDetailVO.memo = response.result.memo
//
//        txidDetailVO.txType = response.result.txtype
//        txidDetailVO.arguments =response.result.arguments
//        txidDetailVO.memo = response.result.memo
//        return txidDetailVO
//
//    }
//
//    /**
//     * 根据交易hash查询交易详细信息
//     */
//    override fun getChainTxidDetailInfo(txid: String): TxidDetailInfoVO {
//        val response= client().getTxDetail(txid)
//        validate(response)
//
//        val txDetailInfoVO = TxidDetailInfoVO()
//        txDetailInfoVO.addr = response.result.addr
//        txDetailInfoVO.txtype = response.result.txtype
//        if(response.result.money == null) {
//            txDetailInfoVO.money = BigDecimal.ZERO
//        } else {
//            txDetailInfoVO.money = BigDecimal(response.result.money)
//        }
//        if (null == response.result.fees) {
//            txDetailInfoVO.fees = BigDecimal.ZERO
//        } else {
//            txDetailInfoVO.fees = response.result.fees.toBigDecimal()
//        }
//
//        txDetailInfoVO.desaddr = response.result.desaddr
//        txDetailInfoVO.regid = response.result.regid
//        txDetailInfoVO.hash = response.result.hash
//        txDetailInfoVO.height = response.result.height
//        txDetailInfoVO.blockhash = response.result.blockhash
//        txDetailInfoVO.confirmHeight = response.result.confirmedheight
//        txDetailInfoVO.confirmedtime = response.result.confirmedtime
//        txDetailInfoVO.contract = response.result.arguments
//        txDetailInfoVO.rawtx = response.result.rawtx
//        return txDetailInfoVO
//    }
//
//
//    override fun sendTransaction(coinSendTransactionPO: CoinSendTransactionPO): String? {
//
//        var sendToAddressPO = SendToAddressPO()
//        sendToAddressPO.sendAddress = coinSendTransactionPO.sendAddress
//        sendToAddressPO.recvAddress = coinSendTransactionPO.recvAddress
//        sendToAddressPO.amount = coinSendTransactionPO.amount
//        var reponse= client().sendToAddress(sendToAddressPO)
//        validate(reponse)
//
//        return reponse.result.hash
//    }
//
//    override fun genAddress(addressType:Int?): CoinAddressVO {
//        var coinAddressVO = CoinAddressVO()
//        var reponse = client().newAddress
//        validate(reponse)
//        coinAddressVO.address = reponse.result.addr
//        coinAddressVO.symbol = getCoinSymbol()
//
//        return coinAddressVO
//    }
//
//    override fun submitOfflineTransaction(coinOfflineTransactionPO: CoinOfflineTransactionPO): String? {
//
//        var reponse= client().submitTx(coinOfflineTransactionPO.offlineTransactionInfo!!)
//        validate(reponse)
//        return reponse.result.hash
//    }
//
//    override fun getChainInfo(): WiccInfoJsonRpcResponse? {
//        var resp =  client().info
//        validate(resp)
//        return resp
//    }
//
//    override fun registerAccountTx(queryAccountInfoPO: QueryAccountInfoPO): String? {
//        val response = client().activeAddress(queryAccountInfoPO.address)
//        validate(response)
//        return response.result.hash
//    }
//
//
//    override fun createContract(createContractPO: CreateContractPO): String? {
//
//        val response = client().createContract(createContractPO)
//        validate(response)
//        return response.result.hash
//    }
//
//    override fun getBlockCount(): Long? {
//        val response = client().getBlockCount()
//        validate(response)
//        return response.result;
//    }
//
//    override fun getBlockByHeight(height: Int): WiccBlockResult{
//        val response = client().getBlock(height)
//        validate(response)
//        return response.result
//    }
//
//    override fun getBlockHash(blockId:Int): String? {
//        val response = client().getBlockHash(blockId)
//        validate(response)
//        return response.result.hash;
//    }
//
//    override fun getTotalCoin(): BigDecimal? {
//        val response = client().getTotalCoin()
//        validate(response)
//        return response.result?.totalCoin
//    }
//
//    private fun validate(resp: BaseJsonRpcResponse?){
//        if(resp == null)
//            throw  NullPointerException("response must not be null")
//
//        resp.validate()
//
//    }
//
//    override fun <T: BaseTx> transferTx(txString: String, txClass: Class<T>): T {
//
//        return JSONObject.parseObject(txString, txClass)
//    }


    private fun client() = wiccMethodClient.getClient()
    @Autowired lateinit var wiccMethodClient: WiccMethodClient


}