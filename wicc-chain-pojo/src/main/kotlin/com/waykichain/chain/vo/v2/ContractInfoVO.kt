package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/15 10:38
 *
 * @Description:    智能合约信息
 *
 */
class ContractInfoVO : Serializable {

    @ApiModelProperty("智能合约地址")
    var address: String? = null

    @ApiModelProperty("智能合约 regid")
    var contractregid: String? = null

    @ApiModelProperty("智能合约描述")
    var contractmemo: String? = null

    @ApiModelProperty("智能合约详情")
    var contractcontent: String? = null
}