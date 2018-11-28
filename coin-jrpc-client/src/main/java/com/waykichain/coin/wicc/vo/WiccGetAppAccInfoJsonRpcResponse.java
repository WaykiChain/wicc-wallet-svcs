package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

@Data
public class WiccGetAppAccInfoJsonRpcResponse {

    private String id;
    private WiccGetAppAccInfoResult result;
    private ErrorInfo error;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WiccGetAppAccInfoResult getResult() {
        return result;
    }

    public void setResult(WiccGetAppAccInfoResult result) {
        this.result = result;
    }

    public ErrorInfo getError() {
        return error;
    }

    public void setError(ErrorInfo error) {
        this.error = error;
    }
}
