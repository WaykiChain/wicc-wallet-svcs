package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/13 15:34
 *
 * @Description:    $des
 *
 */
class ListTxVO:Serializable {

    @ApiModelProperty("已确认的交易哈希列表")
    var confirmtx: MutableList<String>? = null

    @ApiModelProperty("未确认的交易哈希列表")
    var unconfirmtx: MutableList<String>? = null

}
