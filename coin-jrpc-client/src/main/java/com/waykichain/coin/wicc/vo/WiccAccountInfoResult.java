package com.waykichain.coin.wicc.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccAccountInfoResult {

    public String Address;
    public String KeyID;
    public String RegID;
    public String PublicKey;
    public String MinerPKey;
    public BigDecimal Balance;
    public Long Votes;
    public Integer UpdateHeight;
    public String postion;
    public String position;
    public List<VoteFund>voteFundList;
}

@Data
class VoteFund {
    public String address;
    public Long value;
}
