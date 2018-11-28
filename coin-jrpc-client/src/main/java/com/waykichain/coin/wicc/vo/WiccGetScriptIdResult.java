package com.waykichain.coin.wicc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WiccGetScriptIdResult {

    @JsonProperty("regid:")
    private String regid;

    private String script;

}
