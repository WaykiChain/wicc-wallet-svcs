package com.waykichain.chain

import com.alibaba.fastjson.JSONObject
import com.google.gson.Gson
import com.waykichain.coin.wicc.vo.tx.BaseTx
import com.waykichain.coin.wicc.vo.tx.BaseTxDetailVO
import com.waykichain.coin.wicc.vo.tx.BlockRewardTx
import com.waykichain.coin.wicc.vo.tx.BlockRewardTxDetailVO
import org.junit.Test
import org.springframework.beans.BeanUtils

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/8/16 11:53
 *
 * @Description:    $des
 *
 */
class Test {


    @Test
    fun getTranDetailFromRpcPlus() {

        var vo: BaseTxDetailVO? = null


        var blockRewardTx = BlockRewardTx()
        blockRewardTx.tx_type = "COMMON_TX"
        blockRewardTx.block_hash = "ddddd"
        blockRewardTx.reward_value = 100L
        blockRewardTx.txid = "sdfsfsf"
        var json = Gson().toJson(blockRewardTx)

        var txdetail = JSONObject.parseObject(json, BlockRewardTx::class.java)

        var baseTx = txdetail as BaseTx
        println("baseTx = ${Gson().toJson(baseTx)}")


        var blockRewardTx2 = BlockRewardTx()
//        BeanUtils.copyProperties(txdetail, blockRewardTx)
        var detail = BlockRewardTxDetailVO()
//        detail.rewardvalue = blockRewardTx.reward_value
        vo = detail
        vo.blockhash = txdetail.block_hash
        vo.txtype = txdetail.tx_type
        println(Gson().toJson(vo))

    }


}