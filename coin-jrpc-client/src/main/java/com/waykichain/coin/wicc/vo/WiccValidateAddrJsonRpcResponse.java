package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/12 13:43
 * @Description: $des
 */
@Data
public class WiccValidateAddrJsonRpcResponse extends BaseJsonRpcResponse{

    private WiccValidateAddrResult result;
}
