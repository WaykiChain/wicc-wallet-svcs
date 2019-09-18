package com.waykichain.coin.wicc.po;

import lombok.Data;

/**
 * Created by yehuan on 2019/7/10
 */

@Data
public class SubmitDexCancelOrderTxPO {

    private String address  ;

    private String txid ;

    private Long fee = 10000L ;

}

