package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

import java.util.List;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccListAddressJsonRpcResponse {

    private String id;
    private List<WiccListAddressResult> result;
    private ErrorInfo error;

}
