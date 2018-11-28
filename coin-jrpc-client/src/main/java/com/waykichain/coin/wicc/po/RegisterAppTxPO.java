package com.waykichain.coin.wicc.po;

import lombok.Data;

@Data
public class RegisterAppTxPO {

    private String addr;
    private String filepath;
    private Long fee;
    private Integer height;
    private String scriptdescription;

}
