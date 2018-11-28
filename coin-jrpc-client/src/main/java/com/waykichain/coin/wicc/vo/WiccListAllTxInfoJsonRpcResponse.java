package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccListAllTxInfoJsonRpcResponse {

    private String id;
    private WiccListAllTxInfoResult result;
    private ErrorInfo error;

}
