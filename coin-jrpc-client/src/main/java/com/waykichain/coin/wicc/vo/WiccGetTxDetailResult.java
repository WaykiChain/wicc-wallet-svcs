package com.waykichain.coin.wicc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccGetTxDetailResult {
    private String hash;
    private String txtype;
    private Integer ver;
    private String regid;
    private String addr;
    private String desregid;
    private String desaddr;
    private Long money;

    private Long fees;
    private Integer height;
    public String memo;
    public String arguments;

    private String blockhash;
    private Integer confirmedheight;
    private Long confirmedtime;
    private String rawtx;
    private String pubkey;
    @JsonProperty("miner_pubkey")
    private String minerpubkey;
    private String script;
    @JsonProperty("listOutput")
    private List<String> listOutput;

    private List<OperVoteFund> operVoteFundList;

}

