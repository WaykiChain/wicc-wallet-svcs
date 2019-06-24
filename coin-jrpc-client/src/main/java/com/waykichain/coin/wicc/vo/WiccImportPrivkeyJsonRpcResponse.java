package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/13 13:46
 * @Description: $des
 */
@Data
public class WiccImportPrivkeyJsonRpcResponse extends BaseJsonRpcResponse{

    private WiccImportPrivkeyResult result;
}
