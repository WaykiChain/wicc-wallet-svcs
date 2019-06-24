package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/13 13:48
 *
 * @Description:    导入私钥
 *
 */
class ImportPrivateKeyPO {

    @ApiModelProperty("普通账户地址私钥（WIF格式）")
    @NotBlank(message = "[in param error] privkey is null")
    var privkey: String? = null

//    @ApiModelProperty("标签 (optional)")
//    var label: String? = null
//
//    @ApiModelProperty("是否重新扫描交易 (optional, default=true)")
//    var rescan: Boolean? = null
}