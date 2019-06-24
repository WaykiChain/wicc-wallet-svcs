package com.waykichain.chain.commons.biz.xservice.impl

import com.waykichain.chain.biz.domain.BcWiccSendTransactionLog
import com.waykichain.chain.commons.biz.dict.ContractDataType
import com.waykichain.chain.commons.biz.dict.ErrorCode
import com.waykichain.chain.commons.biz.dict.TransactionConstantDict
import com.waykichain.chain.commons.biz.service.BcWiccContractInfoService
import com.waykichain.chain.commons.biz.service.BcWiccSendTransactionLogService
import com.waykichain.chain.commons.biz.xservice.ContractXService
import com.waykichain.chain.commons.biz.xservice.WiccMethodClient
import com.waykichain.chain.contract.util.ContractUtil
import com.waykichain.chain.po.v2.*
import com.waykichain.chain.vo.v2.*
import com.waykichain.coin.wicc.po.*
import com.waykichain.commons.base.BizResponse
import groovy.xml.Entity.not
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.stream.Collectors

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 15:49
 *
 * @Description:    智能合约相关
 *
 */
@Service
class ContractXServiceImpl: ContractXService {

    /**
     * 创建调用智能合约交易
     *
     * [RPC-callcontracttx]
     */
    override fun callContracttx(po: CallContracttxPO): BizResponse<CallContracttxVO> {
        var createContractPO = CreateContractPO()
        createContractPO.appid = po.regid
        createContractPO.userregid = po.calleraddress
        createContractPO.contract = po.arguments
        createContractPO.fee = po.fee
        createContractPO.amount = po.amount
        val response = wiccMethodClient.getClient().createContract(createContractPO)
        var bizResponse = BizResponse<CallContracttxVO>()
        if (response.result != null) {
            var callContracttxVO = CallContracttxVO()
            callContracttxVO.hash = response.result.hash
            bizResponse.data = callContracttxVO
        } else {
            if (response.error != null)  {
                bizResponse.code = response.error.code
                bizResponse.msg = response.error.message
            } else {
                logger.error("callContracttx() error,response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }

        return bizResponse
    }

    /**
     * 管理员创建调用智能合约交易
     *
     * [RPC-callcontracttx]
     */
    override fun sendContractTx(po : SendContractPO): BizResponse<CallContracttxVO>  {

        val bcWiccContractInfo = bcWiccContractInfoService.getByAddress(
                 po.regid,
                po.contractAdminType?:TransactionConstantDict.SENDCONTRACT_DEFAULT_ADMIN_TYPE.numValue.toInt())
        var createContractTxPO = CreateContractTxPO()
        createContractTxPO.appId = bcWiccContractInfo.contractAddressRegId
        createContractTxPO.amount = po.amount
        createContractTxPO.fee = po.fee?:TransactionConstantDict.SENDCONTRACT_DEFAULT_FEE.numValue
        createContractTxPO.contract = po.parameter
        createContractTxPO.userregid = bcWiccContractInfo.adminAddress

        var bcWiccSendTransactionLog = BcWiccSendTransactionLog()
        bcWiccSendTransactionLog.transFee = createContractTxPO.fee
        bcWiccSendTransactionLog.amount = po.amount!!.plus( bcWiccSendTransactionLog.transFee)
        bcWiccSendTransactionLog.transAmount =bcWiccSendTransactionLog.amount

        bcWiccSendTransactionLog.recvAddress = po.regid
        bcWiccSendTransactionLog.requestUuid = po.requestUUID
        bcWiccSendTransactionLog.prameter = po.parameter
        bcWiccSendTransactionLog.status = 100
        bcWiccSendTransactionLog = bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)

        var response= wiccMethodClient.getClient().WiccCreateContractTx(createContractTxPO)

        var bizResponse = BizResponse<CallContracttxVO>()
        if (response.result != null) {
            var callContracttxVO = CallContracttxVO()
            callContracttxVO.hash = response.result.hash
            bizResponse.data = callContracttxVO
            bcWiccSendTransactionLog.txid = response.result.hash
        } else {
            if (response.error != null)  {
                bcWiccSendTransactionLog.remark = "[${response.error.code}],${response.error.message} "
                bizResponse.code = response.error.code
                bizResponse.msg = response.error.message
            } else {
                logger.error("sendContractTx() error,response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        bcWiccSendTransactionLogService.save(bcWiccSendTransactionLog)
        return bizResponse
    }



    /**
     *
     * 获取智能合约相关原生数据信息
     *
     * [RPC-getcontractdata]
     */
    override fun getContradtData(po: ContractdataPO): BizResponse<ContractDataDetailVO> {

        var bizResponse = BizResponse<ContractDataDetailVO>()
        if (!ContractDataType.values().toList().stream().map { it.code }.collect(Collectors.toList()).contains(po.returndatatype)) {
            bizResponse.code = ErrorCode.PARAM_ERROR.code
            bizResponse.msg =  ErrorCode.PARAM_ERROR.msg
            return bizResponse
        }

        var getContractDataPO = GetContractDataPO()
        getContractDataPO.contractRegid = po.regid
        getContractDataPO.key = ContractUtil.toHexString(po.key)
        var response = wiccMethodClient.getClient().getContractDataRaw(getContractDataPO)
        if (response.result != null) {

            var contractDataVO = ContractDataDetailVO()
            contractDataVO.regid = po.regid
            contractDataVO.key = po.key
            var hexData = response.result.value
            contractDataVO.value = when {
                ContractDataType.CONTRACT_DATA_STRING.code == po.returndatatype -> ContractUtil.hexToString(hexData)
                ContractDataType.CONTRACT_DATA_NUMBER.code == po.returndatatype -> ContractUtil.upsidedownHex(hexData).toLong(16)
                else -> hexData
            }
            bizResponse.data = contractDataVO
        } else {
            if (response.error != null)  {
                bizResponse.code = response.error.code
                bizResponse.msg = response.error.message
            } else {
                logger.error("getContradtData() error,response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        return bizResponse
    }

    /**
     * 获取智能合约的regid
     *
     * [RPC-getcontractregid]
     */
    override fun getContractRegid(txHash:String):BizResponse<ContractRegidVO>  {
        var getContractRegidPO = GetContractRegidPO()
        getContractRegidPO.hash = txHash
        val response = wiccMethodClient.getClient().getContractRegid(getContractRegidPO)
        var bizResponse = BizResponse<ContractRegidVO>()
        if (response.result != null) {

            var contractRegidVO = ContractRegidVO()
            contractRegidVO.regid = response.result.regid
            bizResponse.data = contractRegidVO
        } else {
            if (response.error != null)  {
                bizResponse.code = response.error.code
                bizResponse.msg = response.error.message
            } else {
                logger.error("getContractRegid() error,response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        return bizResponse
    }


    /**
     * 获取汇率
     * [RPC-getcontractdata]
     * key:"key_tokenrate"
     */
    override fun getExchangeRate(regId:String): BizResponse<BigDecimal> {

        var po = GetContractDataPO()
        po.contractRegid = regId
        po.key = ContractUtil.toHexString("key_tokenrate");
        var response = wiccMethodClient.getClient().getScriptData(po)

        var bizResponse = BizResponse<BigDecimal>()
        if (response.result != null) {
            bizResponse.data = ContractUtil.upsidedownHex(response.result.value?:"0").toLong(16).toBigDecimal().divide(10000.toBigDecimal(), 8, RoundingMode.HALF_DOWN)
        } else {
            if (response.error != null)  {
                bizResponse.code = response.error.code
                bizResponse.msg = response.error.message
            } else {
                logger.error("getExchangeRate() error,response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }

        return bizResponse
    }

    /**
     * 获取智能合约信息
     *
     * [RPC-getcontractinfo]
     */
    override fun getContractInfo(po: ContractInfoPO):  BizResponse<ContractInfoVO> {

        var contractInfoPO = GetContractInfoPO()
        contractInfoPO.regid = po.regid
        val response = wiccMethodClient.getClient().getContractInfo(contractInfoPO)
        var bizResponse = BizResponse<ContractInfoVO>()
        if (response.result != null) {
            var vo = ContractInfoVO()
            vo.contractregid = response.result.contract_regid
            vo.contractmemo = response.result.contract_memo
            vo.contractcontent = ContractUtil.ConvertContractData(response.result.contract_content)
            bizResponse.data = vo
        } else {
            if (response.error != null)  {
                bizResponse.code = response.error.code
                bizResponse.msg = response.error.message
            } else {
                logger.error("getContractInfo() error,response=$response")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }

        return bizResponse
    }


    /**
     * 获取用户在智能合约中的相关信息
     *
     * [RPC-getcontractaccountinfo]
     */
    override fun getContractAccountInfo(po: ContractAccountInfoPO):  BizResponse<ContractAccountInfoVO> {

        var getAppAccInfoPO = GetAppAccInfoPO()
        getAppAccInfoPO.address = po.address
        getAppAccInfoPO.scriptid = po.contractregid
        val response = wiccMethodClient.getClient().getappaccinfo(getAppAccInfoPO)
        var bizResponse = BizResponse<ContractAccountInfoVO>()
        if (response.result != null) {
            var accountInfoVO = ContractAccountInfoVO()
            accountInfoVO.maccuserid = response.result.mAccUserID
            accountInfoVO.freevalues = response.result.freeValues
            var freezedFunds = response.result.vFreezedFund
            if (freezedFunds != null && freezedFunds.size > 0) {
                accountInfoVO.frozenfunds = mutableListOf()
                for (freeze in freezedFunds){
                    accountInfoVO.frozenfunds!!.add(freeze as FrozenFund)
                }
            }
            bizResponse.data = accountInfoVO
        } else {
            if (response.error != null)  {
                bizResponse.code = response.error.code
                bizResponse.msg = response.error.message
            } else {
                logger.error("getContractAccountInfo() error,response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }

        return bizResponse
    }


    private var logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var wiccMethodClient: WiccMethodClient

    @Autowired
    lateinit var bcWiccContractInfoService: BcWiccContractInfoService

    @Autowired
    lateinit var bcWiccSendTransactionLogService: BcWiccSendTransactionLogService

}
