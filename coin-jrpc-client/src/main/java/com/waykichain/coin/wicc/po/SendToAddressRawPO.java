package com.waykichain.coin.wicc.po;

import lombok.Data;

@Data
public class SendToAddressRawPO {

    private Long fee;
    private Long amount;
    private String srcaddress;
    private String recvaddress;
    private Integer height;

}


