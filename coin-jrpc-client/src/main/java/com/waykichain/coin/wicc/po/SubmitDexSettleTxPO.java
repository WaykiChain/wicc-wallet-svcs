package com.waykichain.coin.wicc.po;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;

/**
 * Created by yehuan on 2019/7/4
 */
@Data
public class SubmitDexSettleTxPO {

    private String settlerAddress;

    private String fee = "WICC:10000:sawi";

    private ArrayList<DealItem> dealItems ;

    public String dealItemsToJsonString(){
        if(dealItems == null || dealItems.isEmpty()) {
            throw new IllegalArgumentException("the dealitem must have some data");
        }
        return JSONObject.toJSONString(dealItems) ;
    }

    @Data
    public static class DealItem{

        private String buy_order_id ;
        private String sell_order_id;
        private Long deal_price;
        private Long deal_coin_amount;
        private Long deal_asset_amount ;

    }

}
