package com.waykichain.coin.wicc.po;

import lombok.Data;

/**
 * Created by yehuan on 2019/7/6
 */

@Data
public class SubmitDexBuyMarketOrderPO {

    private String address ;

    private String coinType ;

    private String assetType ;

    private Long coinAmount ;

    private Long fee = 10000L ;


}
