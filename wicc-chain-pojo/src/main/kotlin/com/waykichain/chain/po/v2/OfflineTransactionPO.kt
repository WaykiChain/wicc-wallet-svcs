package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 14:52
 *
 * @Description:    离线交易
 *
 */
class OfflineTransactionPO: Serializable {


    @ApiModelProperty("已创建的交易签名字段")
    @NotBlank(message = "[in param error] signedTransaction is null")
    var rawtx:String ?= null
}