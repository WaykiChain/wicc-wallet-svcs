package com.waykichain.chain.contract.wusd

import com.waykichain.chain.contract.wusd.domain.WusdConstants

class BetByAdminDomain: TransferWithFeeByAdminDomain() {

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_BET_BY_ADMIN
    }

}