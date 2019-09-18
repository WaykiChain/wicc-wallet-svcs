package com.waykichain.coin.wicc.po;

import lombok.Data;

/**
 * Created by yehuan on 2019/7/8
 */

@Data
public class GetMedianPricePO {

    private String coinType ; //WICC ,WGRT

    private String priceType; //USD ,CNY

    //optinal,default is the tip block height
    private Long height ;

}
