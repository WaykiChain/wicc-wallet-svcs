package com.waykichain.coin.wicc.vo;

import lombok.Data;

import java.util.List;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccBlockResult {
    String hash;
    Integer confirmations;
    Integer size;
    Integer height;
    Integer version;
    String merkleroot;
    Integer txnumber;
    List<String> tx;
    Long time;
    Long nonce;
    String chainwork;
    Integer fuel;
    Integer fuelrate;
    String previousblockhash;
    String nextblockhash;


}
