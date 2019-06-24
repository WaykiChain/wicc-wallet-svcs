package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable
import javax.validation.constraints.Max
import javax.validation.constraints.Min

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/4 20:21
 *
 * @Description:    交易记录查询
 *
 */
class TransactionQueryPO: Serializable,BasePageablePO() {


    @ApiModelProperty("交易地址(转出/转入)")
    @NotBlank(message = "【入参有误】交易地址不能为空")
    var address:String ?=null

    @ApiModelProperty(value = "交易方向 0-所有 1-转出 2-转入", example = "0")
    @Min(value=0,message = "【入参有误】交易方向")
    @Max(value=2,message = "【入参有误】交易方向")
    var trandirection: Int ?= null

    @ApiModelProperty(value = "起始高度", example = "1")
    @Min(value=1, message = "【入参有误】起始高度")
    var startheight:Int?= null

    @ApiModelProperty(value = "交易类型 (不传/空:所有交易, REWARD_TX:系统分红，REG_ACCT_TX:钱包注册，COMMON_TX:普通转账，CONTRACT_TX合约交易，REG_APP_TX:合约发布，DELEGATE_TX:投票交易)")
    var txtype: String? = null
}