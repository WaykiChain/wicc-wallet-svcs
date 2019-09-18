package com.waykichain.coin.wicc.vo;

import lombok.Data;

/**
 * Created by yehuan on 2019/7/29
 */
@Data
public class GetTxReceiptResult {

    private String tx_type;
    private String from_uid  ;
    private String to_uid ;
    private String coin_symbol ;
    private Long transfer_amount ;

}
