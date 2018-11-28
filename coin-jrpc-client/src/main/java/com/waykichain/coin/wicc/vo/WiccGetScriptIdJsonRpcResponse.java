package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

@Data
public class WiccGetScriptIdJsonRpcResponse {

    private String id;
    private WiccGetScriptIdResult result;
    private ErrorInfo error;

}
