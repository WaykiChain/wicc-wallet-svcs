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
    public Integer timeoffset;
    public String proxy;
    public String nettype;
    public Integer genblock;
    public String chainwork;
    public Long tipblocktime;
    public BigDecimal paytxfee;
    public BigDecimal relayfee;
    public Integer fuelrate;
    public Integer fuel;
    public String confdir;
    public String datadir;
    public Integer syncblockheight;
    public Integer tipblockheight;
    public String tipblockhash;
    public Integer connections;
    public String errors;

}
