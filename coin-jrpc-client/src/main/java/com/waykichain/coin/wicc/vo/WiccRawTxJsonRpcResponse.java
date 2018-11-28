package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

@Data
public class WiccRawTxJsonRpcResponse {

    private String id;
    private WiccRawTxResult result;
    private ErrorInfo error;

}
