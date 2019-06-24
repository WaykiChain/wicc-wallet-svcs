package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

@Data
public class WiccGetBlockHashJsonRpcResponse extends BaseJsonRpcResponse{

    private WiccBlockHashResult result;

}
