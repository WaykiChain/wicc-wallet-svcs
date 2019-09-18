package com.waykichain.coin.wicc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccGetTxDetailResult {

    private String txid;
    private String tx_type;
    private Integer ver;
    private String tx_uid;
    private String from_addr;
    private String to_addr;
    private String to_uid;
    private Integer valid_height;
    private Long transer_bcoins;
    private Long fees;
    public String memo;
    private String rawtx;
    private String tx_coin_type = "WICC";
    private String fees_coin_type = "WICC";
    private Integer confirmed_height;
    private Long confirmed_time;
    private String block_hash;
    private Long money;
    private String arguments;
    private String pubkey;
    private String miner_pubkey;
    private String script;

    private List<VoteItem> candidate_votes;
    private List<String> list_out_put;


}

class VoteItem{

    private String vote_type;
    private String candidate_uid;
    private Long voted_bcoins;
}