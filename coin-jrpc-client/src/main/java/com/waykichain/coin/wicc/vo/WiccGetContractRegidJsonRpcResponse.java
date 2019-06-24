package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

@Data
public class WiccGetContractRegidJsonRpcResponse extends BaseJsonRpcResponse{

    private WiccGetContractRegidResult result;

}
