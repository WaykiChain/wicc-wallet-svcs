package com.waykichain.chain.commons.biz.xservice.impl

import com.waykichain.bet.commons.biz.exception.BizException
import com.waykichain.chain.commons.biz.constant.WebapiConstant
import com.waykichain.chain.commons.biz.dict.ErrorCode
import com.waykichain.chain.commons.biz.utils.MyBeanUtils
import com.waykichain.chain.commons.biz.xservice.CdpXService
import com.waykichain.chain.commons.biz.xservice.WiccMethodClient
import com.waykichain.chain.po.v2.CdpRedeemTxPO
import com.waykichain.chain.po.v2.CdpStakeTxPO
import com.waykichain.chain.vo.v2.*
import com.waykichain.coin.wicc.po.CdpTxPO
import com.waykichain.coin.wicc.vo.CdpInfoJsonRpcResponse
import com.waykichain.coin.wicc.vo.CdpRedeemJsonRpcResponse
import com.waykichain.coin.wicc.vo.CdpStakeJsonRpcResponse
import com.waykichain.coin.wicc.vo.UserCdpJsonRpcResponse
import com.waykichain.commons.base.BizResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/8/16 21:18
 *
 * @Description:    cdp 相关
 *
 */
@Service
private class CdpXServiceImpl: CdpXService {



    override fun getCdpInfo(cdpid: String): BizResponse<CdpInfoVO> {

        var response: CdpInfoJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().getCdpInfo(cdpid)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getCdpInfo", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<CdpInfoVO>()
        if (response.result != null) {
            var cdpInfoVO = CdpInfoVO()
            MyBeanUtils.copyProperties(response.result, cdpInfoVO)
            bizResponse.data = cdpInfoVO
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


    override fun getUserCdp(address: String): BizResponse<UserCdpVO> {

        var response: UserCdpJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().getUserCdp(address)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getUserCdp", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<UserCdpVO>()
        if (response.result != null) {
            var userCdpVO = UserCdpVO()
            response.result.user_cdps?.forEach {
                var detail = CdpInfoDetailVO()
                MyBeanUtils.copyProperties(it, detail)
                userCdpVO.usercdps.add(detail)
            }
            bizResponse.data = userCdpVO
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

    override fun cdpStake(po: CdpStakeTxPO): BizResponse<CdpStakeTxVO> {

        var response: CdpStakeJsonRpcResponse?
        try {
            var cdpTxPO = CdpTxPO()
            cdpTxPO.address = po.address
            cdpTxPO.stakecombomoney = po.stakecombomoney!!.toPlainString()
            cdpTxPO.mintcombomoney = po.mintcombomoney!!.toPlainString()
            cdpTxPO.cdpid = po.cdpid
            cdpTxPO.fee = po.feesymbol + ":" + po.fee!!.toPlainString() + WebapiConstant.COIND_UNIT_SAWI
            response = wiccMethodClient.getClient().submitStakeCdpTx(cdpTxPO)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] cdpStake", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<CdpStakeTxVO>()
        if (response.result != null) {
            var cdpStakeTxVO = CdpStakeTxVO()
            cdpStakeTxVO.hash = response.result
            bizResponse.data = cdpStakeTxVO
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

    fun cdpRedeem(po: CdpRedeemTxPO): BizResponse<CdpRedeemTxVO> {

        var response: CdpRedeemJsonRpcResponse?
        try {
            var cdpTxPO = CdpTxPO()
            cdpTxPO.address = po.address
            cdpTxPO.repayamount = po.repayamount!!.toPlainString()
            cdpTxPO.redeemamount = po.redeemamount!!.toPlainString()
            cdpTxPO.cdpid = po.cdpid
            cdpTxPO.fee = po.feesymbol + ":" + po.fee!!.toPlainString() + WebapiConstant.COIND_UNIT_SAWI
            response = wiccMethodClient.getClient().submitRedeemCdpTx(cdpTxPO)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] cdpRedeem", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<CdpRedeemTxVO>()
        if (response.result != null) {
            var cdpRedeemTxVO = CdpRedeemTxVO()
            cdpRedeemTxVO.hash = response.result
            bizResponse.data = cdpRedeemTxVO
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

    private var logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var wiccMethodClient: WiccMethodClient

}