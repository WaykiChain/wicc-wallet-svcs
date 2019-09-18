package com.waykichain.coin.wicc.vo;

import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/9/6 15:24
 * @Description: $des
 */
@Data
public class DexSysOrdersResultDetail {

    private String order_id;
    private String generate_type;
    private String order_type;
    private String order_side;
    private String coin_symbol;
    private String asset_symbol;
    private Long coin_amount;
    private Long asset_amount;
    private Long price;
    private String tx_cord;
    private String user_regid;
    private Long total_deal_coin_amount;
    private Long total_deal_asset_amount;
}
