package com.waykichain.coin.wicc.vo;

import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/9/4 21:21
 * @Description: $des
 */
@Data
public class GetDexSysOrdersJsonRpcResponse extends BaseJsonRpcResponse {
    private GetDexSysOrdersResult result;
}
