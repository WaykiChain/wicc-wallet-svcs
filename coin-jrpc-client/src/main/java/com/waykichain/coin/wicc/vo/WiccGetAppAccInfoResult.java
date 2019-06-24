package com.waykichain.coin.wicc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class WiccGetAppAccInfoResult {

    public String mAccUserID;

    @JsonProperty("FreeValues")
    private Long FreeValues;
    public List<Object> vFreezedFund;

    public BigDecimal tokenAmount;

  }
