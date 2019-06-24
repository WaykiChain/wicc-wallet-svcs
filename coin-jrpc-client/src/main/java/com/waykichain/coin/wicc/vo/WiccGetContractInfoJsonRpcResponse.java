package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/15 10:31
 * @Description: $des
 */
@Data
public class WiccGetContractInfoJsonRpcResponse extends BaseJsonRpcResponse{

    private WiccGetContractInfoResult result;
}
