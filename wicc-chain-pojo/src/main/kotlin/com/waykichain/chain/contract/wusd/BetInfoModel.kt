package com.waykichain.chain.contract.wusd


class BetInfoModel:TransferModel() {

    var betRule: Int = 0
    var betComment: String = "00000000"
    var betOption: Char = 'D'
    var betOdds: String = "1:1"

}