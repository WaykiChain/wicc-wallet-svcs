package com.waykichain.coin.wicc.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/9/4 21:10
 * @Description: 系统挂单
 */
@Data
public class GetDexSysOrdersResult {

    private Long begin_height;

    private Long end_height;

    private Boolean has_more;

    private String last_pos_info;

    private Integer count;

    private List<DexSysOrdersResultDetail> orders;
}


//{
//    "begin_height" : 48277,
//    "end_height" : 48277,
//    "has_more" : false,
//    "last_pos_info" : "",
//    "count" : 1,
//    "orders" : [
//        {
//        "order_id" : "79eff1e67143931e612c6a555933ea7e822bfe04cd792964781735c86d918c2f",
//        "generate_type" : "SYSTEM_GEN_ORDER",
//        "order_type" : "MARKET_PRICE",
//        "order_side" : "BUY",
//        "coin_symbol" : "WUSD",
//        "asset_symbol" : "WGRT",
//        "coin_amount" : 1604458,
//        "asset_amount" : 0,
//        "price" : 0,
//        "tx_cord" : "48277-2",
//        "user_regid" : "1000-1",
//        "total_deal_coin_amount" : 0,
//        "total_deal_asset_amount" : 0
//       }
//    ]
//}
