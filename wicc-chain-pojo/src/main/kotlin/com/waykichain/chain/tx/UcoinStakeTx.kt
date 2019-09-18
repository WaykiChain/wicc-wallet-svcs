package com.waykichain.chain.tx

import com.waykichain.coin.wicc.vo.tx.BaseTx

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/8/15 20:37
 *
 * @Description:    $des
 *
 */
class UcoinStakeTx: BaseTx() {

    var signature: String? = null

    var stake_type: String? = null
}