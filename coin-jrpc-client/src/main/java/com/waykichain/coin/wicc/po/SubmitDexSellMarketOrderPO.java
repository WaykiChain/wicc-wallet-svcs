package com.waykichain.coin.wicc.po;

import lombok.Data;

/**
 * Created by yehuan on 2019/7/6
 */

@Data
public class SubmitDexSellMarketOrderPO {

    private String address ;

    private String coinType ;

    private String assetType ;

    private Long assetAmount ;

    private Long fee = 10000L ;


}
