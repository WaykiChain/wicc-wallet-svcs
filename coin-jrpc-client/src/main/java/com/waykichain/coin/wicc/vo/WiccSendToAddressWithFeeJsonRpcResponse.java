package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/13 14:52
 * @Description: 从源地址账户转账到目的地址账户,手动设置手续费
 */
@Data
public class WiccSendToAddressWithFeeJsonRpcResponse extends BaseJsonRpcResponse {

    private WiccSendToAddressWithFeeResult result;
}
