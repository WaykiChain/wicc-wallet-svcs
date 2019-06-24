package com.waykichain.chain.vo.v2

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/13 13:53
 *
 * @Description:    导入私钥
 *
 */
class ImportPrivateKeyVO: Serializable {

    @ApiModelProperty("普通账户地址")
    var importkeyaddress: String? = null
}