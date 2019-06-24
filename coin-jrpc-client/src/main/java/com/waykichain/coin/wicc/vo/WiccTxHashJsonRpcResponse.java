package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

@Data
public class WiccTxHashJsonRpcResponse extends BaseJsonRpcResponse {

    private WiccTxHashResult result;

}
