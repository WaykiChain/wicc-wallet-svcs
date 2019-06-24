package com.waykichain.coin.wicc.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WiccTotalCoinResult {

    @JsonProperty("TotalCoin")
    private BigDecimal totalCoin;

    private BigDecimal total_coins;

    private Integer total_regids;

}
