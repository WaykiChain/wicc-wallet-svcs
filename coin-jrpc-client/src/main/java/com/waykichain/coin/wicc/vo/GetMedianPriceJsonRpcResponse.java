package com.waykichain.coin.wicc.vo;

import lombok.Data;

/**
 * Created by yehuan on 2019/7/8
 */

@Data
public class GetMedianPriceJsonRpcResponse extends BaseJsonRpcResponse {

    private GetMedianPriceResult result ;
}
