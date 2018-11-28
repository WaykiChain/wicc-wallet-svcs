package com.waykichain.chain.contract.lottery

import com.waykichain.chain.contract.wusd.TransferWithoutFeeDomain

class LotteryOpenPrizeDomain: TransferWithoutFeeDomain() {

    init {
        type = LotteryConstants.WUSD_CONTRACT_TYPE_LOTTERY_OPEN_PRIZE
    }

}