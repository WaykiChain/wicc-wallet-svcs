package com.waykichain.coin.wicc.po;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/13 14:53
 * @Description: 从源地址账户转账到目的地址账户,手动设置手续费
 */
@Data
public class GenSendToAddressTxRawPO {

    private BigDecimal fee;

    private BigDecimal amount;

    private String sender;

    private String recviver;

    private Long height;

}
