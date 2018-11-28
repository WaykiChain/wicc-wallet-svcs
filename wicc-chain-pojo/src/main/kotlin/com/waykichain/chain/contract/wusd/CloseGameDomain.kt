package com.waykichain.chain.contract.wusd

import com.waykichain.chain.contract.wusd.domain.WusdConstants

class CloseGameDomain: TransferWithFeeByAdminDomain(){

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_CLOSE_GAME_BY_ADMIN
    }

}