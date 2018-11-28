package com.waykichain.coin.wicc.vo;

import lombok.Data;

import java.math.BigDecimal;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccListAddressResult {

    public String addr;
    public BigDecimal balance;
    public Boolean haveminerkey;
    public String regid;
}
