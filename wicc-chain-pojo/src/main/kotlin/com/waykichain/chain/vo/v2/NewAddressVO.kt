package com.waykichain.chain.vo.v2

import com.waykichain.chain.po.v2.BasePO
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/4 16:45
 *
 * @Description:    创建新地址
 *
 */
class NewAddressVO: Serializable {

    @ApiModelProperty("维基币地址")
    var addr: String? = null

    @ApiModelProperty("地址对应的私钥")
    var privkey : String? = null

}