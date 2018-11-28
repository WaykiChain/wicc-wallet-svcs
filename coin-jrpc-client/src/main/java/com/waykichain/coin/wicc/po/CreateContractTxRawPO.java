package com.waykichain.coin.wicc.po;

import lombok.Data;

@Data
public class CreateContractTxRawPO {

    private Long fee;
    private Long amount;
    private String addr;
    private String appid;
    private String contract;
    private Integer height;

}
