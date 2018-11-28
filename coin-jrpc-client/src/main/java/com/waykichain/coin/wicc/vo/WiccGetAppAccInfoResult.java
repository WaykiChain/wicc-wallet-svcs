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

    public String getmAccUserID() {
        return mAccUserID;
    }

    public void setmAccUserID(String mAccUserID) {
        this.mAccUserID = mAccUserID;
    }

    public Long getFreeValues() {
        return FreeValues;
    }

    public void setFreeValues(Long freeValues) {
        FreeValues = freeValues;
    }

    public List<Object> getvFreezedFund() {
        return vFreezedFund;
    }

    public void setvFreezedFund(List<Object> vFreezedFund) {
        this.vFreezedFund = vFreezedFund;
    }

    public BigDecimal getTokenAmount() {
        return tokenAmount;
    }

    public void setTokenAmount(BigDecimal tokenAmount) {
        this.tokenAmount = tokenAmount;
    }
}
