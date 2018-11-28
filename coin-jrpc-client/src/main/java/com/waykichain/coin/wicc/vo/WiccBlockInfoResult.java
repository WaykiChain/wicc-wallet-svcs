package com.waykichain.coin.wicc.vo;

import lombok.Data;

import java.math.BigDecimal;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccBlockInfoResult {

    private String chain;
    private Integer blocks;
    private String bestblockhash;
    private BigDecimal verificationprogress;
    private String chainwork;

}
