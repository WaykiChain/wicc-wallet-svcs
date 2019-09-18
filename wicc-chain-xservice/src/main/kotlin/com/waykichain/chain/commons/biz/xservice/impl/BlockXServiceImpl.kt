package com.waykichain.chain.commons.biz.xservice.impl

import com.waykichain.bet.commons.biz.exception.BizException
import com.waykichain.chain.commons.biz.dict.ErrorCode
import com.waykichain.chain.commons.biz.xservice.BlockXService
import com.waykichain.chain.commons.biz.xservice.WiccMethodClient
import com.waykichain.chain.po.v2.BlockHashPO
import com.waykichain.chain.po.v2.BlockInfoPO
import com.waykichain.chain.vo.v2.BlockHashVO
import com.waykichain.chain.vo.v2.BlockInfoDetailVO
import com.waykichain.chain.vo.v2.BlockInfoVO
import com.waykichain.coin.wicc.vo.WiccBlockCountJsonRpcResponse
import com.waykichain.coin.wicc.vo.WiccBlockJsonRpcResponse
import com.waykichain.coin.wicc.vo.WiccGetBlockHashJsonRpcResponse
import com.waykichain.coin.wicc.vo.WiccInfoJsonRpcResponse
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
        var response: WiccBlockCountJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().getBlockCount()
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getBlockCount() error!", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }

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
        var response: WiccGetBlockHashJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().getBlockHash(po.height)
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getBlockHash() error!", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<BlockHashVO>()
        if (response.result != null) {
            val vo = BlockHashVO()
            vo.hash = response.result.txid
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
            vo.hash = response.result.block_hash
            vo.confirmations = response.result.confirmations
            vo.size = response.result.size
            vo.height= response.result.height
            vo.version = response.result.version
            vo.merkleroot = response.result.merkle_root
            vo.txnumber = response.result.tx_count
            vo.tx = response.result.tx
            vo.time = response.result.time
            vo.previousblockhash = response.result.previous_block_hash
            vo.nextblockhash = response.result.next_block_hash
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

        var response: WiccInfoJsonRpcResponse?
        try {
            response = wiccMethodClient.getClient().info
        } catch (e: Exception) {
            logger.error("[JsonRpc request error] getClient() info error!", e)
            throw BizException(ErrorCode.RPC_REQUEST_ERROR)
        }
        var bizResponse = BizResponse<BlockInfoDetailVO>()
        if (response.result != null) {
            var blockInfo = BlockInfoDetailVO()
            blockInfo.version = response.result.version
            blockInfo.fullversion = response.result.full_version
            blockInfo.protocolversion = response.result.protocol_version
            blockInfo.walletversion = response.result.wallet_version
            blockInfo.balance = response.result.wallet_balance
            blockInfo.timeoffset = response.result.time_offset.toLong()
            blockInfo.proxy = response.result.proxy
            blockInfo.nettype = response.result.net_type
//            blockInfo.chainwork = response.result.chainwork
            blockInfo.tipblocktime = response.result.tipblock_time
            blockInfo.paytxfee = response.result.miner_fee_perkb
            blockInfo.relayfee = response.result.relay_fee_perkb
            blockInfo.fuelrate = response.result.tipblock_fuel_rate
            blockInfo.fuel = response.result.tipblock_fuel
            blockInfo.datadirectory = response.result.data_dir
            blockInfo.tipblockhash = response.result.tipblock_hash
            blockInfo.syncheight = response.result.syncblock_height.toLong()
            blockInfo.connections = response.result.connections
            blockInfo.errors = response.result.errors
            blockInfo.blocks = response.result.syncblock_height.toLong()

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