package com.waykichain.chain.commons.biz.xservice.impl

import com.waykichain.chain.commons.biz.dict.ErrorCode
import com.waykichain.chain.commons.biz.xservice.BlockXService
import com.waykichain.chain.commons.biz.xservice.WiccMethodClient
import com.waykichain.chain.po.v2.BlockHashPO
import com.waykichain.chain.po.v2.BlockInfoPO
import com.waykichain.chain.vo.v2.BlockHashVO
import com.waykichain.chain.vo.v2.BlockInfoDetailVO
import com.waykichain.chain.vo.v2.BlockInfoVO
import com.waykichain.coin.wicc.vo.WiccBlockJsonRpcResponse
import com.waykichain.commons.base.BizResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 15:09
 *
 * @Description:    区块相关
 *
 */
@Service
class BlockXServiceImpl: BlockXService {


    /**
     * 获取区块高度
     */
    override fun getBlockCount(): BizResponse<Long> {
        val response = wiccMethodClient.getClient().getBlockCount()

        var bizResponse = BizResponse<Long>()
        if (response.result != null) {
            bizResponse.data = response.result
        } else {
            if (response.error != null) {
                bizResponse.code = response.error.code
                bizResponse.msg = response.error.message
            } else {
                logger.error("getBlockCount() error, response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }

        return bizResponse
    }

    /**
     * 查询区块hash
     */
    override fun getBlockHash(po: BlockHashPO): BizResponse<BlockHashVO> {
        val response = wiccMethodClient.getClient().getBlockHash(po.height)
        var bizResponse = BizResponse<BlockHashVO>()
        if (response.result != null) {
            val vo = BlockHashVO()
            vo.hash = response.result.hash
            bizResponse.data = vo
        } else {
            if (response.error != null) {
                bizResponse.code = response.error.code
                bizResponse.msg = response.error.message
            } else {
                logger.error("getBlockHash() error, response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }

        return bizResponse
    }


    /**
     * 根据区块高度获取区块信息
     */
    override fun getBlock(po: BlockInfoPO): BizResponse<BlockInfoVO> {

        var response: WiccBlockJsonRpcResponse
        try {
            var height: Int = po.heightorhash!!.toInt()
            response = wiccMethodClient.getClient().getBlock(height)
        } catch (e: Exception) {
            response = wiccMethodClient.getClient().getBlock(po.heightorhash)
        }

        var bizResponse = BizResponse<BlockInfoVO>()
        if (response.result != null) {
            val vo = BlockInfoVO()
            vo.hash = response.result.hash
            vo.confirmations = response.result.confirmations
            vo.size = response.result.size
            vo.height= response.result.height
            vo.version = response.result.version
            vo.merkleroot = response.result.merkleroot
            vo.txnumber = response.result.txnumber
            vo.tx = response.result.tx
            vo.time = response.result.time
            vo.previousblockhash = response.result.previousblockhash
            vo.nextblockhash = response.result.nextblockhash
            vo.nonce = response.result.nonce
            vo.fuel = response.result.fuel
            vo.fuelrate = response.result.fuelrate
            vo.chainwork = response.result.chainwork
            bizResponse.data = vo
        } else {
            if (response.error != null) {
                bizResponse.code = response.error.code
                bizResponse.msg = response.error.message
            } else {
                logger.error("getBlockByHeight() error, response=${response.toString()}")
                bizResponse.code = ErrorCode.RPC_RESPONSE_IS_NULL.code
                bizResponse.msg =  ErrorCode.RPC_RESPONSE_IS_NULL.msg
            }
        }
        return bizResponse
    }


    /**
     * 获取区块信息[RPC-getinfo]
     */
    override fun getChainInfo(): BizResponse<BlockInfoDetailVO> {

        val response = wiccMethodClient.getClient().info
        var bizResponse = BizResponse<BlockInfoDetailVO>()
        if (response.result != null) {
            var blockInfo = BlockInfoDetailVO()
            blockInfo.version = response.result.version
            blockInfo.fullversion = response.result.fullversion
            blockInfo.protocolversion = response.result.protocolversion
            blockInfo.walletversion = response.result.walletversion
            blockInfo.balance = response.result.balance
            blockInfo.timeoffset = response.result.timeoffset.toLong()
            blockInfo.proxy = response.result.proxy
            blockInfo.nettype = response.result.nettype
            blockInfo.chainwork = response.result.chainwork
            blockInfo.tipblocktime = response.result.tipblocktime
            blockInfo.paytxfee = response.result.paytxfee
            blockInfo.relayfee = response.result.relayfee
            blockInfo.fuelrate = response.result.fuelrate
            blockInfo.fuel = response.result.fuel
            blockInfo.datadirectory = response.result.datadir
            blockInfo.tipblockhash = response.result.tipblockhash
            blockInfo.syncheight = response.result.syncblockheight.toLong()
            blockInfo.connections = response.result.connections
            blockInfo.errors = response.result.errors
            blockInfo.blocks = response.result.syncblockheight.toLong()

            bizResponse.data = blockInfo
        } else {
            if (response.error != null) {
                bizResponse.code = response.error.code
                bizResponse.msg = response.error.message
            } else {
                logger.error("getChainInfo() error, response=${response.toString()}")
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