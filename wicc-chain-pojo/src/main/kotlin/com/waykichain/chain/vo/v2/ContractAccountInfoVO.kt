package com.waykichain.chain.vo.v2

import com.google.common.collect.ImmutableList
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 18:02
 *
 * @Description:    用户在智能合约中的相关信息
 *
 */
class ContractAccountInfoVO: Serializable {

    @ApiModelProperty(" 查询的用户地址转成16进制字符串")
    var maccuserid: String? = null

    @ApiModelProperty("智能合约的自由可操作的币的数量")
    var freevalues: Long? = null

    @ApiModelProperty("智能合约的冻结的币的数量和解冻时间的数据列表")
    var frozenfunds: MutableList<FrozenFund>? = null
}

class FrozenFund: Serializable {

    @ApiModelProperty("冻结金额")
    var value:BigDecimal? = null

    @ApiModelProperty("高度")
    var nHeight:Long? = null

    @ApiModelProperty("")
    var vTag:String ?= null
}