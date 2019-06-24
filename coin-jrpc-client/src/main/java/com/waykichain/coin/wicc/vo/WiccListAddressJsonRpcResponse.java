package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

import java.util.List;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccListAddressJsonRpcResponse extends BaseJsonRpcResponse{

    private List<WiccListAddressResult> result;

}
