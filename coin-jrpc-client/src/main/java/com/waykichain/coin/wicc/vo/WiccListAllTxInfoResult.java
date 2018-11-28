package com.waykichain.coin.wicc.vo;

import lombok.Data;

import java.math.BigDecimal;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccListAllTxInfoResult {

    private String hash;
    private String txtype;
    private Integer ver;
    private String regid;
    private String addr;
    private String desregid;
    private String desaddr;
    private BigDecimal money;
    private BigDecimal fees;
    private Integer height;
    public String Contract;
    private String blockhash;
    private Integer confirmHeight;
    private Long confirmedtime;
    private String rawtx;

}
