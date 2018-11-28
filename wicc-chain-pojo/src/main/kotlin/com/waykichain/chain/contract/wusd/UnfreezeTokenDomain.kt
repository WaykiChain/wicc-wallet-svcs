package com.waykichain.chain.contract.wusd

import com.waykichain.chain.contract.wusd.domain.WusdConstants

class UnfreezeTokenDomain:TransferWithoutFeeDomain() {

    init {
        type = WusdConstants.WUSD_CONTRACT_TYPE_UNFREEZ_TOKEN
    }

}