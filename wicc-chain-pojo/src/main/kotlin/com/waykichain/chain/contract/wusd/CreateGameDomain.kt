package com.waykichain.chain.contract.wusd

import com.waykichain.chain.contract.wusd.domain.WusdConstants

class CreateGameDomain: TransferWithFeeByAdminDomain() {

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_CREATE_GAME
    }

}