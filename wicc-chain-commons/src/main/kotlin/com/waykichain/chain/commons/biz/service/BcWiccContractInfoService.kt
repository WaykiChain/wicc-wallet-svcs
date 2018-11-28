package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.BcWiccContractInfo

interface BcWiccContractInfoService {
    fun getById(id:Long): BcWiccContractInfo?

    fun save(bcWiccContractInfo: BcWiccContractInfo): BcWiccContractInfo

    fun getByAddress(contractAddress: String?): BcWiccContractInfo

    fun getByActive(): BcWiccContractInfo
}
