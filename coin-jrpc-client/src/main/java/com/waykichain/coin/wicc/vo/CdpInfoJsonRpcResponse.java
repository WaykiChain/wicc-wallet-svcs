package com.waykichain.coin.wicc.vo;

import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/8/17 11:09
 * @Description: $des
 */
@Data
public class CdpInfoJsonRpcResponse extends BaseJsonRpcResponse {
    private CdpInfoResult result;
}
