package com.waykichain.coin.wicc.vo.tx

/**
 *  Created by yehuan on 2019/7/24
 */

class DelegateVoteTxDetailVO: BaseTxDetailVO(){

    var candidatevotes: ArrayList<VoteItem> = arrayListOf()

    class VoteItem{
        var votetype: String? = null

        var candidateuid: String? = null

        var votedbcoins: Long? = null
    }
}