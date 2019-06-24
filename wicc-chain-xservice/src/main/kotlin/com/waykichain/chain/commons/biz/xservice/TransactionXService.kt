package com.waykichain.chain.commons.biz.xservice

import com.waykichain.chain.po.v2.*
import com.waykichain.chain.vo.v2.*
import com.waykichain.commons.base.BizResponse

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/4 19:53
 *
 * @Description:    交易相关
 *
 */
interface TransactionXService {

    fun getTransactions(po: TransactionQueryPO): BizResponse<WiccTransactionsVO>

    fun getTranDetailByTX(po: WiccTransactionDetailPO): BizResponse<WiccTransactionVO>

    fun getTranDetailFromRPC(hash: String): BizResponse<WiccTransactionVO>

    fun sendtoaddress(po: SendtoaddressPO): BizResponse<SendtoaddressVO>

    fun sendtoaddressWithFee(po: SendtoAddressWithFeePO): BizResponse<SendtoaddressVO>

    fun genSendtoAddresstxraw(po: GenSendToAddressTxrawPO): BizResponse<GenSendToAddressTxrawVO>

    fun listTx(): BizResponse<ListTxVO>

    fun decodeRawtx(po: DecodeRawtxPO): BizResponse<DecodeRawtxVO>

    fun updateUUID(po: UpdateUuidPO): BizResponse<Any>

    fun submitOfflineTransaction(po: OfflineTransactionPO): BizResponse<WiccTransactionVO>

    fun depositTestMoney(recviver:String, money: String): BizResponse<SendtoaddressVO>
}