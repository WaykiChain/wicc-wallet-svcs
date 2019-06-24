package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 10:26
 *
 * @Description:    $des
 *
 */
class WiccTransactionsVO: BasePageableVO() {

    @ApiModelProperty("交易数据")
    var transactions = arrayListOf<WiccTransactionVO>()

    @ApiModelProperty("当前区块高度")
    var currentheight:Int ?= null
}