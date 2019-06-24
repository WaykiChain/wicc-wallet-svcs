package com.waykichain.chainstarter.service

import com.waykichain.chain.po.CoinOfflineTransactionPO
import com.waykichain.chain.po.CoinSendTransactionPO
import com.waykichain.chain.po.QueryAccountInfoPO
import com.waykichain.chain.po.QueryTxidInfoPO
import com.waykichain.chain.vo.*
import com.waykichain.chainstarter.client.WiccMethodClient
import com.waykichain.coin.wicc.po.CreateContractPO
import com.waykichain.coin.wicc.po.SendToAddressPO
import com.waykichain.coin.wicc.vo.BaseJsonRpcResponse
import com.waykichain.coin.wicc.vo.WiccBlockResult
import com.waykichain.coin.wicc.vo.WiccInfoJsonRpcResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal



@Service
open class WaykichainCoinHandler : CoinHandler() {

     init {
         regHandler(getCoinSymbol(), this)
     }

    override fun getCoinSymbol(): String {
        return "WICC"
    }
    override fun getAccountInfo(address: String): AccountInfoVO? {
        var accountInfoResponse = wiccMethodClient.getClient().getAccountInfo(address)
        validate(accountInfoResponse)

        var accountInfoVO = AccountInfoVO()
        if(accountInfoResponse.result != null) {
            accountInfoVO.address = accountInfoResponse.result.Address
            accountInfoVO.balance = accountInfoResponse.result.balance
            accountInfoVO.keyID = accountInfoResponse.result.keyID
            accountInfoVO.minerPKey = accountInfoResponse.result.minerPKey
            accountInfoVO.regID = accountInfoResponse.result.regID
            accountInfoVO.postion = accountInfoResponse.result.position
            accountInfoVO.updateHeight = accountInfoResponse.result.updateHeight
            accountInfoVO.votes = accountInfoResponse.result.votes
            accountInfoVO.publicKey = accountInfoResponse.result.publicKey
        }
        return accountInfoVO

    }

    override fun getBalance(address: String?): BalanceVO? {
        val accountInfo = wiccMethodClient.getClient().getAccountInfo(address)
        validate(accountInfo)

        var balanceVO = BalanceVO()
        balanceVO.balance = accountInfo.result.balance
        balanceVO.number = accountInfo.result.updateHeight
        return balanceVO
    }

    override fun getTxidInfo(queryTxidInfoPO: QueryTxidInfoPO): TxidVO? {

        val resp = wiccMethodClient.getClient().getTxDetail(queryTxidInfoPO.txid)

        validate(resp)

        var txidVO = TxidVO()
        txidVO.symbol = getCoinSymbol()
        txidVO.txid = queryTxidInfoPO.txid
        txidVO.number = resp.result.confirmedheight
        return txidVO
    }

    override fun getChainTxidInfo(txid: String): TxidDetailVO {
        var response= wiccMethodClient.getClient().getTxDetail(txid)

        validate(response)

        var txidDetailVO = TxidDetailVO()
        txidDetailVO.sendAddress = response.result.addr
        txidDetailVO.fee = response.result.fees.toBigDecimal()
        if(response.result.money == null) {
            txidDetailVO.amount = BigDecimal.ZERO
        } else {
            txidDetailVO.amount = BigDecimal(response.result.money)
        }
        txidDetailVO.toAddress = response.result.desaddr
        txidDetailVO.regId = response.result.regid
        txidDetailVO.txid = response.result.hash
        txidDetailVO.contract = response.result.arguments
        if(txidDetailVO.contract == null)
            txidDetailVO.contract =response.result.arguments

        txidDetailVO.arguments = response.result.arguments
        txidDetailVO.memo = response.result.memo

        txidDetailVO.txType = response.result.txtype
        txidDetailVO.arguments =response.result.arguments
        txidDetailVO.memo = response.result.memo
        return txidDetailVO

    }

    /**
     * 根据交易hash查询交易详细信息
     */
    override fun getChainTxidDetailInfo(txid: String): TxidDetailInfoVO {
        val response= wiccMethodClient.getClient().getTxDetail(txid)
        validate(response)

        val txDetailInfoVO = TxidDetailInfoVO()
        txDetailInfoVO.addr = response.result.addr
        txDetailInfoVO.txtype = response.result.txtype
        if(response.result.money == null) {
            txDetailInfoVO.money = BigDecimal.ZERO
        } else {
            txDetailInfoVO.money = BigDecimal(response.result.money)
        }
        if (null == response.result.fees) {
            txDetailInfoVO.fees = BigDecimal.ZERO
        } else {
            txDetailInfoVO.fees = response.result.fees.toBigDecimal()
        }

        txDetailInfoVO.desaddr = response.result.desaddr
        txDetailInfoVO.regid = response.result.regid
        txDetailInfoVO.hash = response.result.hash
        txDetailInfoVO.height = response.result.height
        txDetailInfoVO.blockhash = response.result.blockhash
        txDetailInfoVO.confirmHeight = response.result.confirmedheight
        txDetailInfoVO.confirmedtime = response.result.confirmedtime
        txDetailInfoVO.contract = response.result.arguments
        txDetailInfoVO.rawtx = response.result.rawtx
        return txDetailInfoVO
    }


    override fun sendTransaction(coinSendTransactionPO: CoinSendTransactionPO): String? {

        var sendToAddressPO = SendToAddressPO()
        sendToAddressPO.sendAddress = coinSendTransactionPO.sendAddress
        sendToAddressPO.recvAddress = coinSendTransactionPO.recvAddress
        sendToAddressPO.amount = coinSendTransactionPO.amount
        var reponse= wiccMethodClient.getClient().sendToAddress(sendToAddressPO)
        validate(reponse)

        return reponse.result.hash
    }

    override fun genAddress(addressType:Int?): CoinAddressVO {
        var coinAddressVO = CoinAddressVO()
        var reponse = wiccMethodClient.getClient().newAddress
        validate(reponse)
        coinAddressVO.address = reponse.result.addr
        coinAddressVO.symbol = getCoinSymbol()

        return coinAddressVO
    }

    override fun submitOfflineTransaction(coinOfflineTransactionPO: CoinOfflineTransactionPO): String? {

        var reponse= wiccMethodClient.getClient().submitTx(coinOfflineTransactionPO.offlineTransactionInfo!!)
        validate(reponse)
        return reponse.result.hash
    }

    override fun getChainInfo(): WiccInfoJsonRpcResponse? {
        var resp =  wiccMethodClient.getClient().info
        validate(resp)
        return resp
    }

    override fun registerAccountTx(queryAccountInfoPO: QueryAccountInfoPO): String? {
        val response = wiccMethodClient.getClient().activeAddress(queryAccountInfoPO.address)
        validate(response)
        return response.result.hash
    }


    override fun createContract(createContractPO: CreateContractPO): String? {

        val response = wiccMethodClient.getClient().createContract(createContractPO)
        validate(response)
        return response.result.hash
    }

    override fun getBlockCount(): Long? {
        val response = wiccMethodClient.getClient().getBlockCount()
        validate(response)
        return response.result;
    }

    override fun getBlockByHeight(height: Int): WiccBlockResult{
        val response = wiccMethodClient.getClient().getBlock(height)
        validate(response)
        return response.result
    }

    override fun getBlockHash(blockId:Int): String? {
        val response = wiccMethodClient.getClient().getBlockHash(blockId)
        validate(response)
        return response.result.hash;
    }

    override fun getTotalCoin(): BigDecimal? {
        val response = wiccMethodClient.getClient().getTotalCoin()
        validate(response)
        return response.result?.totalCoin
    }

    fun validate(resp: BaseJsonRpcResponse?){
        if(resp != null)
            resp.validate()
    }

    @Autowired lateinit var wiccMethodClient: WiccMethodClient


}