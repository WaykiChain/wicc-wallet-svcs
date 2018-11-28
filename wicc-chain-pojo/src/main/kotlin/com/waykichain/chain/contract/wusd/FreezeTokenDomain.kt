package com.waykichain.chain.contract.wusd

import com.waykichain.chain.contract.wusd.domain.WusdConstants

class FreezeTokenDomain:TransferWithoutFeeDomain() {

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_FREEZE_TOKEN
    }

}