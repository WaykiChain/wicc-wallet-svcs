package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/13 15:17
 * @Description: 创建交易签名字段,手动设置手续费,可用 submittx 方法进行提交交易
 */
@Data
public class WiccGenSendtoAddressTxrawJsonRpcResponse extends BaseJsonRpcResponse{

    private WiccGenSendtoAddressTxrawResult result;
}
