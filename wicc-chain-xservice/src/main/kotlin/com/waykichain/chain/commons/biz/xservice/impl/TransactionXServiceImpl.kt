package com.waykichain.chain.commons.biz.xservice.impl

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.waykichain.bet.commons.biz.exception.BizException
import com.waykichain.chain.biz.domain.BcWiccOfflineTransacationLog
import com.waykichain.chain.biz.domain.BcWiccSendTransactionLog
import com.waykichain.chain.biz.domain.BcWiccTransaction
import com.waykichain.chain.commons.biz.dict.CoinType
import com.waykichain.chain.commons.biz.dict.ErrorCode
import com.waykichain.chain.commons.biz.dict.TransactionConstantDict
import com.waykichain.chain.commons.biz.dict.TransactionDirectionDict
import com.waykichain.chain.commons.biz.env.Environment
import com.waykichain.chain.commons.biz.service.BcWiccBlockService
import com.waykichain.chain.commons.biz.service.BcWiccOfflineTransacationLogService
import com.waykichain.chain.commons.biz.service.BcWiccSendTransactionLogService
import com.waykichain.chain.commons.biz.service.BcWiccTransactionService
import com.waykichain.chain.commons.biz.utils.MyBeanUtils
import com.waykichain.chain.commons.biz.xservice.BcWiccSendTransactionLogXService
import com.waykichain.chain.commons.biz.xservice.CdpXService
import com.waykichain.chain.commons.biz.xservice.TransactionXService
import com.waykichain.chain.commons.biz.xservice.WiccMethodClient
import com.waykichain.chain.dict.TransactionType
import com.waykichain.chain.po.v2.*
import com.waykichain.chain.vo.v2.*
import com.waykichain.coin.ErrorInfo
import com.waykichain.coin.wicc.po.*
import com.waykichain.coin.wicc.vo.*
import com.waykichain.coin.wicc.vo.tx.*
import com.waykichain.commons.base.BizResponse
import org.apache.commons.lang3.StringUtils
import org.bouncycastle.asn1.x500.style.RFC4519Style.c
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.*

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/4 19:54
 *
 * @Description:    交易相关
 *
 */
@Service
open class TransactionXServiceImpl: TransactionXService {

    /**
     * 根据交易地址获取交易记录
     *
     */
    override fun getTransactions(po: TransactionQueryPO): BizResponse<WiccTransactionsVO> {

        var transactionsVO = WiccTransactionsVO()
        //当前区块高度
        transactionsVO.currentheight = bcWiccBlockService.getLastBlockId()
        var cond = com.waykichain.chain.commons.biz.cond.QueryTransactionCond()
        cond.address = po.address
        cond.startNumber = po.startheight
        cond.tranDirection = po.trandirection?:null
        cond.pageSize = po.pagesize
        cond.currentPage = po.currentpage
        cond.txtype = po.txtype
        cond.coinsymbol = po.coinsymbol
        val result = bcWiccTransactionService.getByAddressV2(cond)

        transactionsVO.currentpage = po.currentpage
        transactionsVO.totalpages = result.second
        transactionsVO.totalcount = result.third

        result.first?.let {
            for (bean in it) {
                val transaction = WiccTransactionVO()
                transaction.money =  if (null != bean.money)  BigDecimal(bean.money) else BigDecimal.ZERO
                transaction.addr = bean.addr
                transaction.desaddr = bean.desaddr
                transaction.symbol = CoinType.WICC.msg
                transaction.hash = bean.txid
                transaction.blockhash = bean.blockHash
                transaction.confirmedtime = Date(bean.confirmedTime)
                transaction.confirmedheight = bean.confirmHeight
                if (null != bean.fees) transaction.fees = BigDecimal(bean.fees)
                transaction.arguments = bean.contract
                transaction.regid = bean.regid
                transaction.desregid = bean.descregid
                transaction.height = bean.height
                transaction.rawtx = bean.rawtx
                transaction.coinsymbol = bean.coinSymbol
                transaction.feesymbol = bean.feeSymbol
                if (null != bean.txType) transaction.txtype = bean.txType
                transaction.trandirection =  if (po.address.equals( bean.addr)) TransactionDirectionDict.TRAN_DIRECTION_IN.numValue
                              else if (po.address.equals( bean.desaddr)) TransactionDirectionDict.TRAN_DIRECTION_OUT.numValue
                              else TransactionDirectionDict.TRAN_DIRECTION_OTHER.numValue
                transactionsVO.transactions!!.add(transaction)
            }

        }

        return BizResponse(transactionsVO)
    }

    /**
     * 根据交易哈希获取交易详情(查本地表)
     */
   override fun getTranDetailByTX(po: WiccTransactionDetailPO): BizResponse<WiccTransactionVO> {

        val bean = bcWiccTransactionService.getByTxid(po.hash!!)
        val transaction = WiccTransactionVO()
        transaction.money =  if (null != bean.money)  BigDecimal(bean.money) else BigDecimal.ZERO
        transaction.addr = bean.addr
        transaction.desaddr = bean.desaddr
        transaction.symbol = CoinType.WICC.msg
        transaction.hash = bean.txid
        transaction.blockhash = bean.blockHash
        transaction.confirmedtime = Date(bean.confirmedTime)
        transaction.confirmedheight = bean.confirmHeight
        if (null != bean.fees) transaction.fees = BigDecimal(bean.fees)
        transaction.arguments = bean.contract
        transaction.regid = bean.regid
        transaction.desregid = bean.descregid
        transaction.height = bean.height
        transaction.rawtx = bean.rawtx
        if (null != bean.txType) transaction.txtype = bean.txType
        return BizResponse(transaction)
    }


    /**
     * 根据交易哈希获取交易详情(调用RPC)
     */
    override fun getTranDetailFromRPC(hash: String): BizResponse<WiccTransactionVO> {

        var txdetail: BaseTx?
        try {
            txdetail = getTxDetail(hash)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getTxDetail()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<WiccTransactionVO>()
        if(txdetail != null) {
            val transaction = WiccTransactionVO()
            transaction.money = txdetail.coin_amount.toBigDecimal()
            transaction.addr = txdetail.from_addr
            transaction.desaddr = txdetail.to_addr
            transaction.symbol = CoinType.WICC.msg
            transaction.hash = txdetail.txid
            transaction.blockhash = txdetail.block_hash
            if (txdetail.confirmed_time != null) transaction.confirmedtime = Date(txdetail.confirmed_time)
            transaction.confirmedheight = txdetail.confirmed_height
            if (null != txdetail.fees) transaction.fees = BigDecimal(txdetail.fees)
            transaction.arguments = txdetail.arguments
            transaction.regid = txdetail.tx_uid
            transaction.desregid = txdetail.to_uid
            transaction.height = txdetail.valid_height
            transaction.rawtx = txdetail.rawtx
            transaction.txtype = txdetail.tx_type

            bizResponse.data = transaction
        } else {
            bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
            bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
        }

        return bizResponse
    }


    /**
     * 根据交易哈希获取链上原始数据(gettxdetail)
     */
    override fun getTxDetailOriginalJson(hash: String): BizResponse<String> {
        val txJsonString = wiccMethodClient.getClient().getTxDetailJson(hash)
        logger.info("[Tx detail from chain] txdetail=$txJsonString")
        return BizResponse(JSONObject.parseObject(txJsonString).getString("result"))
    }


    /**
     * 根据交易哈希获取交易详情(gettxdetail 增强)
     */
    override fun getTranDetailFromRpcPlus(hash: String): BizResponse<BaseTxDetailVO> {

        var bizResponse = BizResponse<BaseTxDetailVO>()
        val txJsonString = wiccMethodClient.getClient().getTxDetailJson(hash)
        logger.info("[Tx detail from chain] txdetail=$txJsonString")
        val jsonObject = JSONObject.parseObject(txJsonString)
        val result = jsonObject.getString("result")
        if (StringUtils.isBlank(result)) {
            val errorInfo = JSONObject.parseObject(jsonObject.getString("error"), ErrorInfo::class.java)
            bizResponse.code = errorInfo.code
            bizResponse.msg =  errorInfo.message
            return bizResponse
        }
        val txType = JSONObject.parseObject(result).getString("tx_type") ?: throw BizException(ErrorCode.TRANSACTION_NO_EXIST_IN_RPC)

        var detail = BaseTxDetailVO()
        when (txType) {
            TransactionType.BLOCK_REWARD_TX.type -> {
                val blockRewardTx = JSONObject.parseObject(result, BlockRewardTx::class.java)
                detail = BlockRewardTxDetailVO()
                MyBeanUtils.copyProperties(blockRewardTx, detail)
            }
            TransactionType.ACCOUNT_REGISTER_TX.type -> {
                val accountRegistTx = JSONObject.parseObject(result, AccountRegistTx::class.java)
                detail = AccountRegistTxDetailVO()
                MyBeanUtils.copyProperties(accountRegistTx, detail)
            }
            TransactionType.BCOIN_TRANSFER_TX.type -> {
                val bCoinTransferTx = JSONObject.parseObject(result, BCoinTransferTx::class.java)
                detail = BCoinTransferTxDetailVO()
                MyBeanUtils.copyProperties(bCoinTransferTx, detail)
            }
            TransactionType.LCONTRACT_DEPLOY_TX.type -> {
                val contractDeployTx = JSONObject.parseObject(result, ContractDeployTx::class.java)
                detail = ContractDeployTxDetailVO()
                MyBeanUtils.copyProperties(contractDeployTx, detail)
            }
            TransactionType.LCONTRACT_INVOKE_TX.type -> {
                val contractInvokeTx = JSONObject.parseObject(result, ContractInvokeTx::class.java)
                detail = ContractInvokeTxDetailVO()
                MyBeanUtils.copyProperties(contractInvokeTx, detail)
            }
            TransactionType.DELEGATE_VOTE_TX.type -> {
                val delegateVoteTx = JSONObject.parseObject(result, DelegateVoteTx::class.java)
                detail = DelegateVoteTxDetailVO()
                MyBeanUtils.copyProperties(delegateVoteTx, detail)
            }
            TransactionType.UCOIN_TRANSFER_TX.type -> {
                val uCoinTransferTx = JSONObject.parseObject(result, UCoinTransferTx::class.java)
                detail = UCoinTransferTxDetailVO()
                MyBeanUtils.copyProperties(uCoinTransferTx, detail)
            }
            TransactionType.UCOIN_REWARD_TX.type -> {
                val uCoinRewardTx = JSONObject.parseObject(result, UCoinRewardTx::class.java)
                detail = UCoinRewardTxDetailVO()
                MyBeanUtils.copyProperties(uCoinRewardTx, detail)
            }
            TransactionType.UCOIN_BLOCK_REWARD_TX.type -> {
                val uCoinBlockRewardTx = JSONObject.parseObject(result, UCoinBlockRewardTx::class.java)
                detail = UCoinBlockRewardTxDetailVO()
                MyBeanUtils.copyProperties(uCoinBlockRewardTx, detail)
            }

            TransactionType.PRICE_FEED_TX.type -> {
                val priceFeedTx = JSONObject.parseObject(result, PriceFeedTx::class.java)
                detail = PriceFeedTxDetailVO()
                MyBeanUtils.copyProperties(priceFeedTx, detail)
            }
            TransactionType.PRICE_MEDIAN_TX.type -> {
                val priceMedianTx = JSONObject.parseObject(result, PriceMedianTx::class.java)
                var priceMedianTxDetail = PriceMedianTxDetailVO()
                MyBeanUtils.copyProperties(priceMedianTx, priceMedianTxDetail)
                priceMedianTx.median_price_points.forEach {
                    var priceItemVO = PriceMedianTxDetailVO.PriceItemVO()
                    priceItemVO.coinsymbol = it.coin_symbol
                    priceItemVO.pricesymbol = it.price_symbol
                    priceItemVO.price = it.price
                    priceMedianTxDetail.medianpricepoints.add(priceItemVO)
                }
                detail = priceMedianTxDetail

            }

            TransactionType.CDP_STAKE_TX.type -> {
                val cdpStakeTx = JSONObject.parseObject(result, CdpStakeTx::class.java)
                var txDetail = CdpStakeTxDetailVO()
                MyBeanUtils.copyProperties(cdpStakeTx, txDetail)
                cdpStakeTx.assets_to_stake.forEach { t, u ->
                    txDetail.assetstostake[t] = u
                }
                detail = txDetail
            }
            TransactionType.CDP_REDEEM_TX.type -> {
                val cdpRedeemTx = JSONObject.parseObject(result, CdpRedeemTx::class.java)
                var txDetail = CdpRedeemTxDetailVO()
                MyBeanUtils.copyProperties(cdpRedeemTx, txDetail)
                cdpRedeemTx.assets_to_redeem.forEach { t, u ->
                    txDetail.assetstoredeem[t] = u
                }
                detail = txDetail
            }
            TransactionType.CDP_LIQUIDATE_TX.type -> {
                val cdpLiquidateTx = JSONObject.parseObject(result, CdpLiquidateTx::class.java)
                MyBeanUtils.copyProperties(cdpLiquidateTx, detail)
            }

            TransactionType.DEX_LIMIT_BUY_ORDER_TX.type -> {
                val cdpLiquidateTx = JSONObject.parseObject(result, DexBuyLimitOrderTx::class.java)
                detail =DexBuyLimitOrderTxDetailVO()
                MyBeanUtils.copyProperties(cdpLiquidateTx, detail)
            }
            TransactionType.DEX_LIMIT_SELL_ORDER_TX.type -> {
                val cdpLiquidateTx = JSONObject.parseObject(result, DexSellLimitOrderTx::class.java)
                detail = DexSellLimitOrderTxDetailVO()
                MyBeanUtils.copyProperties(cdpLiquidateTx, detail)
            }
            TransactionType.DEX_MARKET_BUY_ORDER_TX.type -> {
                val cdpLiquidateTx = JSONObject.parseObject(result, DexBuyMarketOrderTx::class.java)
                detail = DexBuyMarketOrderTxDetailVO()
                MyBeanUtils.copyProperties(cdpLiquidateTx, detail)
            }
            TransactionType.DEX_MARKET_SELL_ORDER_TX.type -> {
                val cdpLiquidateTx = JSONObject.parseObject(result, DexSellMarketOrderTx::class.java)
                detail = DexSellMarketOrderTxDetailVO()
                MyBeanUtils.copyProperties(cdpLiquidateTx, detail)
            }
            TransactionType.DEX_CANCEL_ORDER_TX.type -> {
                val cdpLiquidateTx = JSONObject.parseObject(result, DexCancelOrderTx::class.java)
                detail = DexCancelOrderTxDetailVO()
                MyBeanUtils.copyProperties(cdpLiquidateTx, detail)
            }
            TransactionType.DEX_TRADE_SETTLE_TX.type -> {
                val cdpLiquidateTx = JSONObject.parseObject(result, DexSettleOrderTx::class.java)
                detail = DexSettleOrderTxDetailVO()
                MyBeanUtils.copyProperties(cdpLiquidateTx, detail)
            }

            else -> {
                val baseTx = JSONObject.parseObject(result, BaseTx::class.java)
                MyBeanUtils.copyProperties(baseTx, detail)
            }
        }
        bizResponse.data = detail
        return bizResponse
    }


    /**
     * 从源地址账户转账到目的地址账户，手续费默认为10000sawi [RPC-sendtoaddress]
     */
    @Transactional
    override fun sendtoaddress(po: SendtoaddressPO): BizResponse<SendtoaddressVO> {

//        val oldLog = bcWiccSendTransactionLogService.getByRequestUuid(po.uuid!!)
//        if (oldLog != null ) {
//            return BizResponse(ErrorCode.UUID_IS_USED.code, ErrorCode.UUID_IS_USED.msg)
//        }

        var bcWiccSendTransactionLog = BcWiccSendTransactionLog()
        bcWiccSendTransactionLog.amount = po.amount!!.toLong()
        bcWiccSendTransactionLog.transFee = TransactionConstantDict.SENDTOADDRESS_DEFAULT_FEE.numValue
        bcWiccSendTransactionLog.transAmount =bcWiccSendTransactionLog.amount - bcWiccSendTransactionLog.transFee
        bcWiccSendTransactionLog.sendAddress = po.sender
        bcWiccSendTransactionLog.recvAddress = po.recviver
        bcWiccSendTransactionLog.requestUuid = "auto-"+UUID.randomUUID().toString().replace("-","")
        bcWiccSendTransactionLog = bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
        var sendToAddressPO = SendTxPO()
        sendToAddressPO.sendAddress = po.sender
        sendToAddressPO.receiveAddress = po.recviver
        sendToAddressPO.amount = po.amount

        var response= wiccMethodClient.getClient().sendToAddress(sendToAddressPO)
        var bizResponse = BizResponse<SendtoaddressVO>()
        if (response.result != null) {
            bcWiccSendTransactionLog.txid = response.result.txid
            var sendtoaddressVO = SendtoaddressVO()
            sendtoaddressVO.hash = response.result.txid
            bizResponse.data = sendtoaddressVO
        } else {
            if (response.error != null) {
                bcWiccSendTransactionLog.remark = "[${response.error.code}],${response.error.message} "
                bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)

                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
        return bizResponse
    }

    /**
     * 从源地址账户转账到目的地址账户,手动设置手续费 [RPC-sendtoaddresswithfee]
     */
    @Transactional
    override fun sendtoaddressWithFee(po: SendtoAddressWithFeePO): BizResponse<SendtoaddressVO> {

//        val oldLog = bcWiccSendTransactionLogService.getByRequestUuid(po.uuid!!)
//        if (oldLog != null ) {
//            return BizResponse(ErrorCode.UUID_IS_USED.code, ErrorCode.UUID_IS_USED.msg)
//        }

        var bcWiccSendTransactionLog = BcWiccSendTransactionLog()
        bcWiccSendTransactionLog.amount = po.amount!!.toLong()
        bcWiccSendTransactionLog.transFee = po.fee!!.toLong()
        bcWiccSendTransactionLog.transAmount =bcWiccSendTransactionLog.amount - bcWiccSendTransactionLog.transFee
        bcWiccSendTransactionLog.sendAddress = po.sender
        bcWiccSendTransactionLog.recvAddress = po.recviver
        bcWiccSendTransactionLog.requestUuid = "auto-"+UUID.randomUUID().toString().replace("-","")
        bcWiccSendTransactionLog = bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
        var sendToAddressWithFeePO = SendTxPO()
        sendToAddressWithFeePO.sendAddress = po.sender
        sendToAddressWithFeePO.receiveAddress = po.recviver
        sendToAddressWithFeePO.amount = po.amount
        sendToAddressWithFeePO.fee = po.fee

        var response= wiccMethodClient.getClient().sendToAddressWithFee(sendToAddressWithFeePO)
        var bizResponse = BizResponse<SendtoaddressVO>()
        if (response.result != null) {
            bcWiccSendTransactionLog.txid = response.result.hash
            var sendtoaddressVO = SendtoaddressVO()
            sendtoaddressVO.hash = response.result.hash
            bizResponse.data = sendtoaddressVO
        } else {
            if (response.error != null) {
                bcWiccSendTransactionLog.remark = "[${response.error.code}],${response.error.message} "
                bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)

                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                logger.error("sendtoaddressWithFee() error, response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
        return bizResponse
    }

    /**
     * 创建交易签名字段,手动设置手续费,可用 submittx 方法进行提交交易
     * [RPC-gensendtoaddresstxraw]
     */
//    override fun genSendtoAddresstxraw(po: GenSendToAddressTxrawPO): BizResponse<GenSendToAddressTxrawVO> {
//
//
//        var heightResponse: WiccBlockCountJsonRpcResponse?
//        try {
//            heightResponse = wiccMethodClient.getClient().getBlockCount()
//        } catch (e: Exception) {
//            logger.error("[JsonRpc request error] getBlockCount()", e)
//            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
//        }
//        if (heightResponse == null || heightResponse.result == null) {return BizResponse(ErrorCode.GET_BLOCK_HEIGHT_FAIL.code,ErrorCode.GET_BLOCK_HEIGHT_FAIL.msg) }
//
//        var genSendToAddressTxrawPO = GenSendToAddressTxRawPO()
//        genSendToAddressTxrawPO.fee = po.fee
//        genSendToAddressTxrawPO.amount = po.amount
//        genSendToAddressTxrawPO.sender = po.sender
//        genSendToAddressTxrawPO.recviver = po.recviver
//        genSendToAddressTxrawPO.height = heightResponse.result
//        var response: WiccGenSendtoAddressTxrawJsonRpcResponse?
//        try {
//            response = wiccMethodClient.getClient().genSendtoAddresstxraw(genSendToAddressTxrawPO)
//        } catch (e: Exception) {
//            logger.error("[JsonRpc request error] genSendtoAddresstxraw()", e)
//            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
//        }
//        var bizResponse = BizResponse<GenSendToAddressTxrawVO>()
//        if(response.result != null) {
//            val vo = GenSendToAddressTxrawVO()
//            vo.rawtx = response.result.rawtx
//            bizResponse.data = vo
//        } else {
//            if (response.error != null) {
//                bizResponse.msg = response.error.message
//                bizResponse.code = response.error.code
//            } else {
//                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
//                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
//            }
//        }
//
//        return bizResponse
//    }

    /**
     * 获取当前节点交易列表：包含已确认和未确认的交易
     *
     * [RPC-listtx]
     */
     override fun listTx(): BizResponse<ListTxVO> {

        var response: WiccListTxInfoJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().listTx()
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] listTx()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<ListTxVO>()
        if(response.result != null) {
            val vo = ListTxVO()
            vo.confirmtx = response.result.confirmed_tx
            vo.unconfirmtx = response.result.unconfirmed_tx
            bizResponse.data = vo
        } else {
            if (response.error != null) {
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                logger.error("listTx() error, response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }

        return bizResponse
    }

    /**
     * 根据签名字段解析原始交易单
     *
     * [RPC-decodetxraw]
     */
    override fun decodeRawtx(po: DecodeRawtxPO): BizResponse<DecodeRawtxVO> {

        var decodeRawTxPO = DecodeRawTxPO()
        decodeRawTxPO.hexstring = po.rawtx
        var response: WiccDecodeRawTxJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().decodeRawtx(decodeRawTxPO)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] decodeRawtx()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<DecodeRawtxVO>()

        if(response.result != null) {
            val vo = DecodeRawtxVO()
            BeanUtils.copyProperties(response.result, vo)
            vo.txtype = response.result.tx_type
            vo.addr = response.result.from_addr
            vo.regid = response.result.tx_uid
            vo.desaddr = response.result.to_addr
            vo.desregid = response.result.tx_uid
            vo.hash = response.result.txid
            vo.money =  response.result.coin_amount
            vo.appuid = response.result.app_uid
            vo.memo = response.result.memo
            vo.arguments = response.result.arguments
            bizResponse.data = vo
        } else {
            if (response.error != null) {
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                logger.error("listTx() error, response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }

        return bizResponse
    }

    /**
     * 更新UUID
     */
    override fun updateUUID(po: UpdateUuidPO): BizResponse<Any> {
        bcWiccSendTransactionLogXService.updateBcWiccSendTransactionLogRequestUuid(po.requestUUID!!)
        return BizResponse(null)
    }


    /**
     * 发起离线交易[RPC-sendtxraw]
     */
    override fun submitOfflineTransaction(po: OfflineTransactionPO): BizResponse<WiccTransactionVO> {
        var bcWiccOfflineTransacationLog = BcWiccOfflineTransacationLog()
        bcWiccOfflineTransacationLog.requestUuid  = "auto-"+UUID.randomUUID().toString().replace("-","")
        bcWiccOfflineTransacationLog.info = po.rawtx
        bcWiccOfflineTransacationLog = bcWiccOfflineTransacationLogService.save(bcWiccOfflineTransacationLog)
        var response= wiccMethodClient.getClient().submitTx(po.rawtx)
        var bizResponse = BizResponse<WiccTransactionVO>()

        if (response.result != null) {

            bcWiccOfflineTransacationLog.txid = response.result.txid
            bcWiccOfflineTransacationLogService.save(bcWiccOfflineTransacationLog)
            val result = getTranDetailFromRPC(response.result.txid)
            bizResponse = result
        } else {
            if(response.error != null) {
                bcWiccOfflineTransacationLog.remark = "[${response.error.code}],${response.error.message} "
                bcWiccOfflineTransacationLogService.save(bcWiccOfflineTransacationLog)
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        return bizResponse
    }


    /**
     * 获取测试金额
     * TODO 是否要对每个账户做请求次数限制
     */
    override fun depositTestMoney(recviver:String, coinAmount: String): BizResponse<SendtoaddressVO> {

        var  sendToAddressPO = SendTxPO()
        sendToAddressPO.amount = BigDecimal(coinAmount)
        sendToAddressPO.sendAddress = Environment.TEST_MONEY_SENDER_ADDRESS
        sendToAddressPO.receiveAddress = recviver
        var response: WiccSubmitTxJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().sendToAddress(sendToAddressPO)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] sendToAddress()", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<SendtoaddressVO>()

        if(response.result != null) {
            var sendtoaddressVO = SendtoaddressVO()
            sendtoaddressVO.hash = response.result.txid
            bizResponse.data = sendtoaddressVO
        } else {
            if (response.error != null) {
                bizResponse.msg = response.error.message
                bizResponse.code = response.error.code
            } else {
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        return bizResponse
    }

    override fun tranferTxDetailForDB(txdetail: BaseTx): BcWiccTransaction {
        var  bcWiccTransaction = BcWiccTransaction()
        bcWiccTransaction.blockHash = txdetail.block_hash
        bcWiccTransaction.txid = txdetail.txid
        bcWiccTransaction.txType = txdetail.tx_type
        bcWiccTransaction.ver = txdetail.ver
        bcWiccTransaction.regid = txdetail.tx_uid
        bcWiccTransaction.addr = txdetail.from_addr
        bcWiccTransaction.descregid = txdetail.to_uid
        bcWiccTransaction.desaddr = txdetail.to_addr
        bcWiccTransaction.money = txdetail.coin_amount
        bcWiccTransaction.fees = txdetail.fees
        bcWiccTransaction.height = txdetail.valid_height
        bcWiccTransaction.contract = txdetail.arguments
        bcWiccTransaction.confirmHeight = txdetail.confirmed_height
        bcWiccTransaction.confirmedTime = txdetail.confirmed_time
        bcWiccTransaction.rawtx = txdetail.rawtx
        bcWiccTransaction.coinSymbol = txdetail.coin_symbol
        bcWiccTransaction.feeSymbol = txdetail.fee_symbol
        bcWiccTransaction.blockNumber = txdetail.confirmed_height
        bcWiccTransaction.txContent = JSON.toJSONString(txdetail)

//        bcWiccTransaction.pubkey = it.pubkey
//        bcWiccTransaction.minerPubkey = it.miner_pubkey
//        bcWiccTransaction.script = it.script
//        bcWiccTransaction.listOutput = it.list_out_put?.toString()
//        if (it.candidate_votes != null) bcWiccTransaction.operVoteFundList  = JSON.toJSONString(it.candidate_votes)
        return bcWiccTransaction
    }

    override fun getTxDetail(txHash: String): BaseTx? {
        val txJsonString = wiccMethodClient.getClient().getTxDetailJson(txHash)?: return null
        val result = JSONObject.parseObject(txJsonString).getString("result")?: return null
        val txType = JSONObject.parseObject(result).getString("tx_type")?: return null
        return transferTx(result, TransactionType.parseTransactionTypeToTxClass(txType))
    }


    override fun <T: BaseTx> transferTx(txString: String, txClass: Class<T>): T {
        return JSONObject.parseObject(txString, txClass)
    }


    private var logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var wiccMethodClient: WiccMethodClient

    @Autowired
    lateinit var bcWiccBlockService: BcWiccBlockService

    @Autowired
    lateinit var bcWiccTransactionService: BcWiccTransactionService

    @Autowired
    lateinit var bcWiccSendTransactionLogService: BcWiccSendTransactionLogService

    @Autowired
    lateinit var bcWiccSendTransactionLogXService: BcWiccSendTransactionLogXService

    @Autowired
    lateinit var bcWiccOfflineTransacationLogService: BcWiccOfflineTransacationLogService
}