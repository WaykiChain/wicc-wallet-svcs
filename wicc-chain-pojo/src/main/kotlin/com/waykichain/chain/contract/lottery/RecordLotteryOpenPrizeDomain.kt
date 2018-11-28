package com.waykichain.chain.contract.lottery


class RecordLotteryOpenPrizeDomain: RecordLotteryBetDomain() {

    init {
        type = LotteryConstants.WUSD_CONTRACT_TYPE_RECORD_LOTTERY_OPEN_PRIZE
    }

}