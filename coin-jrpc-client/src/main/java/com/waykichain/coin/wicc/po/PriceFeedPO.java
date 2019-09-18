package com.waykichain.coin.wicc.po;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;

/**
 * Created by yehuan on 2019/7/4
 */
@Data
public class PriceFeedPO {

    private String feederAddress;

    private Long fee;

    private ArrayList<PriceInfo> priceFeeds;

    public String priceToJSONString(){
        if(priceFeeds == null)
            return null;
        return JSONObject.toJSONString(priceFeeds) ;
    }
    @Data
    public static class PriceInfo{

        private String coin; //WICC,WGRT

        private String currency; //USD,CNY

        private Long price;

    }
}

