package com.waykichain.coin.wicc.po;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateContractPO {

    private String userregid;
    private String appid;
    private BigDecimal amount;
    private String contract;
    private BigDecimal fee;
    private Integer height;



}
