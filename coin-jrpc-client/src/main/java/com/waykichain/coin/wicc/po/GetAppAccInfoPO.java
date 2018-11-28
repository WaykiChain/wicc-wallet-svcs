package com.waykichain.coin.wicc.po;

import lombok.Data;

@Data
public class GetAppAccInfoPO {

    private String scriptid;
    private String address;
    private int minconf=1;



}
