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

    private String txid;

    private String tx_type;

    private int ver;

    private String tx_uid;

    private String from_addr;

    private String to_uid;

    private String app_uid;

    private String to_addr;

    private BigDecimal coin_amount;

    private BigDecimal fees;

    private Long valid_height;

    private String arguments;

    private String memo;


}
