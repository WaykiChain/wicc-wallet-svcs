package com.waykichain.coin.wicc.vo.tx

/**
 *  Created by yehuan on 2019/7/24
 */

class DelegateVoteTx: BaseTx(){

    var candidate_votes: ArrayList<VoteItem> = arrayListOf()

    class VoteItem{
        var vote_type: String? = null

        var candidate_uid: CandidateUid? = null

        var voted_bcoins: Long? = null

        class CandidateUid {
            var id_type: String? = null
            var id: String? = null
        }
    }
}
