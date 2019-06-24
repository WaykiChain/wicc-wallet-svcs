package com.waykichain.coin.wicc.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/13 15:46
 * @Description: $des
 */
@Data
public class WiccDecodeRawTxResult {

    private String hash;

    private String txtype;

    private int ver;

    private String regid;

    private String addr;

    private String desregid;

    private String desaddr;

    private BigDecimal money;

    private BigDecimal fees;

    private Long height;

    private String contract;

    private String arguments;
}
