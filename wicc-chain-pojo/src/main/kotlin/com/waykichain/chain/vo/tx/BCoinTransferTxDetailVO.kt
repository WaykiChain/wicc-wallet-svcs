package com.waykichain.coin.wicc.vo.tx

import io.swagger.annotations.ApiModelProperty

class BCoinTransferTxDetailVO: BaseTxDetailVO(){

    @ApiModelProperty("备注")
    var memo: String? = null
}