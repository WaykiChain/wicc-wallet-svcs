package com.waykichain.coin.wicc.vo;

import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/8/17 11:29
 * @Description: $des
 */
@Data
public class UserCdpJsonRpcResponse extends BaseJsonRpcResponse {
    private UserCdpResult result;
}
