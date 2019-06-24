package com.waykichain.coin.wicc.vo;

import com.waykichain.ChainException;
import com.waykichain.coin.ErrorInfo;
import lombok.Data;

/**
 * Created by yehuan on 2019/6/4
 */
@Data
public abstract  class BaseJsonRpcResponse {
    private String id;
    private ErrorInfo error;

    public void validate(){
        if(error != null)
            throw new ChainException(error);
    }
}
