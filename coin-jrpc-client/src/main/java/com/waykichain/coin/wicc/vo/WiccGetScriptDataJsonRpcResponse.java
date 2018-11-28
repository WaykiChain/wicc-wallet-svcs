package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

@Data
public class WiccGetScriptDataJsonRpcResponse {

    private String id;
    private WiccGetScriptDataResult result;
    private ErrorInfo error;

}
