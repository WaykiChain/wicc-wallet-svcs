package com.waykichain.coin.wicc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccInfoResult {

    public Long version;
    public String fullversion;
    public Long protocolversion;
    public Long walletversion;
    public BigDecimal balance;
    public Integer blocks;
    public Integer timeoffset;
    public String connections;
    public String proxy;
    public String nettype;
    public String chainwork;
    public Long tipblocktime;
    public BigDecimal paytxfee;
    public BigDecimal relayfee;
    public Integer fuelrate;
    public Integer fuel;
    @JsonProperty("data directory")
    public String datadirectory;
    public Integer syncheight;
    @JsonProperty("tip block hash")
    public String tipblockhash;
    public String errors;

}
