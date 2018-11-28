package com.waykichain.chain.contract.lottery

import com.waykichain.chain.contract.wusd.TransferWithoutFeeDomain

class LotteryBetDomain: TransferWithoutFeeDomain() {

    init {
        type = LotteryConstants.WUSD_CONTRACT_TYPE_LOTTERY_BET
    }

}