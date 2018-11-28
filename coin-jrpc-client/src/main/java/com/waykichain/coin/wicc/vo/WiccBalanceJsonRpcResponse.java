package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccBalanceJsonRpcResponse {

    private String id;
    private WiccBalanceResult result;
    private ErrorInfo error;

}
