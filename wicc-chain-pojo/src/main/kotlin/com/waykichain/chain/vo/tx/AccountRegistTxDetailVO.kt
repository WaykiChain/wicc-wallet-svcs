package com.waykichain.coin.wicc.vo.tx

import io.swagger.annotations.ApiModelProperty

/**
 *  Created by yehuan on 2019/7/24
 */

class AccountRegistTxDetailVO: BaseTxDetailVO(){

    @ApiModelProperty("账户公钥")
    var pubkey: String? = null

    @ApiModelProperty("矿工公钥")
    var minerpubkey: String?  = null

}