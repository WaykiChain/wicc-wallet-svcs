package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;


@Data
public class WiccTotalCoinJsonRpcResponse extends BaseJsonRpcResponse {

    private WiccTotalCoinResult result;
}

