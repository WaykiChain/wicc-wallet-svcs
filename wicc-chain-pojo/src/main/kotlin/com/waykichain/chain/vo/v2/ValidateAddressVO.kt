package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/12 12:01
 *
 * @Description:    检查普通地址或者合约地址是否有效
 *
 */
class ValidateAddressVO {

    @ApiModelProperty("true-有效 false-无效")
    var ret: Boolean? = null
}