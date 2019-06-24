package com.waykichain.chain.controller

import com.waykichain.chain.commons.biz.xservice.BlockXService
import com.waykichain.chain.po.v2.BlockHashPO
import com.waykichain.chain.po.v2.BlockInfoPO
import com.waykichain.chain.vo.v2.BlockHashVO
import com.waykichain.chain.vo.v2.BlockInfoDetailVO
import com.waykichain.chain.vo.v2.BlockInfoVO
import com.waykichain.commons.base.BizResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 15:05
 *
 * @Description:    区块相关
 *
 */
@RestController
@RequestMapping("/block")
@Api(description = "block|区块相关")
class BlockController {

    @PostMapping("/getblockcount")
    @ApiOperation(value = "【Get the current block height】【获取当前区块高度】",
            notes = "",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun getBlockCount(): BizResponse<Long> {
        return blockXService.getBlockCount()
    }

    @PostMapping("/getinfo")
    @ApiOperation(value = "【Get the details of wallet】【获取钱包节点详细信息】",
            notes = "",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun getChainInfo(): BizResponse<BlockInfoDetailVO> {
        return blockXService.getChainInfo()
    }

    @PostMapping("/getblockhash")
    @ApiOperation(value = "【Get the block hash based on the block height】【根据区块高度获取区块哈希】",
            notes = "",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun getBlockHash(@RequestBody @Valid po: BlockHashPO): BizResponse<BlockHashVO> {
        return blockXService.getBlockHash(po)
    }

    @PostMapping("/getblock")
    @ApiOperation(value = "【Get block details based on block height or block hash】【根据区块高度/区块哈希获取区块详情】",
            notes = "",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun getBlock(@RequestBody @Valid po: BlockInfoPO): BizResponse<BlockInfoVO> {
        return blockXService.getBlock(po)
    }

    @Autowired
    lateinit var blockXService: BlockXService
}