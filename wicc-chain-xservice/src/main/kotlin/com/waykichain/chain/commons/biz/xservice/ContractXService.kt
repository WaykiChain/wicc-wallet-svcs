package com.waykichain.chain.commons.biz.xservice

import com.waykichain.chain.po.v2.*
import com.waykichain.chain.vo.v2.*
import com.waykichain.coin.wicc.po.CreateContractPO
import com.waykichain.commons.base.BizResponse
import java.math.BigDecimal

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 16:11
 *
 * @Description:    智能合约相关
 *
 */
interface ContractXService {

    /**
     * 创建调用智能合约交易
     *
     * [RPC-callcontracttx]
     */
    fun callContracttx(po: CallContracttxPO): BizResponse<CallContracttxVO>

    /**
     * 创建调用智能合约交易()指定管理员账户
     *
     * [RPC-callcontracttx]
     */
    fun sendContractTx(po : SendContractPO): BizResponse<CallContracttxVO>

    /**
     *
     * 获取智能合约相关原生数据信息
     *
     * [RPC-getcontractdata]
     */
    fun getContradtData(po: ContractdataPO): BizResponse<ContractDataDetailVO>

    /**
     * 获取智能合约的regid
     *
     * [RPC-getcontractregid]
     */
    fun getContractRegid(txHash:String):BizResponse<ContractRegidVO>

    /**
     * 获取汇率
     * [RPC-getcontractdata]
     * key:"key_tokenrate"
     */
    fun getExchangeRate(regId:String): BizResponse<BigDecimal>

    /**
     * 获取用户在智能合约中的相关信息
     *
     * [RPC-getcontractaccountinfo]
     */
    fun getContractAccountInfo(po: ContractAccountInfoPO):  BizResponse<ContractAccountInfoVO>

    /**
     * 获取智能合约信息
     *
     * [RPC-getcontractinfo]
     */
    fun getContractInfo(po: ContractInfoPO):  BizResponse<ContractInfoVO>

}