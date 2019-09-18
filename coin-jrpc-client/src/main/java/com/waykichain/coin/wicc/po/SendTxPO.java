package com.waykichain.coin.wicc.po;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/8/30 21:17
 * @Description: $des
 */
@Data
public class SendTxPO {

    public String sendAddress;
    public String receiveAddress;
    public BigDecimal amount;
    public BigDecimal fee = new BigDecimal("10000");
    public String coinSymbol = "WICC";
    public String feeSymbol = "WICC";
    public String unit = "sawi";

}
