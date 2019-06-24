package com.waykichain.chain.commons.biz.xservice

import com.waykichain.chain.po.v2.BlockHashPO
import com.waykichain.chain.po.v2.BlockInfoPO
import com.waykichain.chain.vo.v2.BlockHashVO
import com.waykichain.chain.vo.v2.BlockInfoDetailVO
import com.waykichain.chain.vo.v2.BlockInfoVO
import com.waykichain.commons.base.BizResponse

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 15:09
 *
 * @Description:    区块相关
 *
 */
interface BlockXService {

    fun getBlockCount(): BizResponse<Long>

    fun getChainInfo(): BizResponse<BlockInfoDetailVO>

    fun getBlockHash(po: BlockHashPO): BizResponse<BlockHashVO>

    fun getBlock(po: BlockInfoPO): BizResponse<BlockInfoVO>



}