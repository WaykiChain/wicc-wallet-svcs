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
    public String full_version;
    public Long protocol_version;
    public Long wallet_version;
    public BigDecimal wallet_balance;
    public Integer time_offset;
    public String proxy;
    public String net_type;
    public Integer gen_block;
    public BigDecimal miner_fee_perkb;
    public BigDecimal relay_fee_perkb;
    public String conf_dir;
    public String data_dir;
    public Integer tipblock_fuel_rate;
    public Integer tipblock_fuel;
    public Integer syncblock_height;
    public Integer tipblock_height;
    public String  tipblock_hash;
    public Long tipblock_time;
    public Integer connections;
    public Integer block_interval;
    public String errors;

}
