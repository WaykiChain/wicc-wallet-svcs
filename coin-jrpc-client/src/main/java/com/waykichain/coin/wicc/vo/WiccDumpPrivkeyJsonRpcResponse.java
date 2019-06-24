package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/12 20:00
 * @Description: $des
 */
@Data
public class WiccDumpPrivkeyJsonRpcResponse extends BaseJsonRpcResponse {

    private WiccDumpPrivkeyResult result;
}
