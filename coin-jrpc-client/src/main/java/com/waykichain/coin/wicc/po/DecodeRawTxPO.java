package com.waykichain.coin.wicc.po;

import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/13 15:45
 * @Description: 根据签名字段解析原始交易单
 */
@Data
public class DecodeRawTxPO {

    private String hexstring;
}
