package com.waykichain.coin.wicc.po;

import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/8/17 11:32
 * @Description: $des
 */
@Data
public class CdpTxPO {
    private String address;
    private String cdpid;
    private String repayamount;
    private String redeemamount;
    private String stakecombomoney;
    private String mintcombomoney;
    private String liquidateamount;
    private String fee;
}
