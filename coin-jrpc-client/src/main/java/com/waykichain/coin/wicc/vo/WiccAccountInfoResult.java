package com.waykichain.coin.wicc.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccAccountInfoResult {

    private String address;
    private String keyid;
    private String nickid;
    private String regid;
    private boolean regid_mature;
    private String owner_pubkey;
    private String miner_pubkey;
    private HashMap<String, AccountTokenInfo> tokens;

    private Long received_votes;
    private String position;
    private List<VoteFund> vote_list;


    @Data
    public static class CandidateUid {

        private String id_type;
        private String id;

    }

    @Data
    public static class VoteFund {
        private String vote_type;
        private CandidateUid candidate_uid;

        private Long voted_bcoins;
    }

    @Data
    public static class AccountTokenInfo {

        private Long free_amount;
        private Long staked_amount;
        private Long frozen_amount;

    }
}
