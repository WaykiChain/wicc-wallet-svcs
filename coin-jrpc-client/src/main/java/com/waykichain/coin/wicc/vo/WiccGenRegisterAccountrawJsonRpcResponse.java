package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/13 10:03
 * @Description: $des
 */
@Data
public class WiccGenRegisterAccountrawJsonRpcResponse extends BaseJsonRpcResponse {

    private WiccGenRegisterAccountrawResult result;
}
