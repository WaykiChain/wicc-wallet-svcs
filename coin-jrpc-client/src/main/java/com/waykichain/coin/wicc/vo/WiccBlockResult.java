package com.waykichain.coin.wicc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccBlockResult {
//    @JsonProperty("block_hash")
    String block_hash;
    Integer confirmations;
    Integer size;
    Integer height;
    Integer version;
    String merkle_root;
    Integer tx_count;
    List<String> tx;
    Long time;
    Long nonce;
    String previous_block_hash;
    String next_block_hash;
    List<Object> median_price;

    String chainwork;
    Integer fuel;
    Integer fuelrate;


}
