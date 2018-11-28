package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.bet.commons.biz.exception.BizException
import com.waykichain.chain.biz.domain.BcWiccContractInfo
import com.waykichain.chain.biz.domain.QBcWiccContractInfo
import com.waykichain.chain.commons.biz.dict.ErrorCode
import com.waykichain.chain.commons.biz.repository.mysql.BcWiccContractInfoRepository
import com.waykichain.chain.commons.biz.service.BcWiccContractInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BcWiccContractInfoServiceImpl: BcWiccContractInfoService {

    override fun getByActive(): BcWiccContractInfo {
        val bcWiccContractInfo = bcWiccContractInfoRepository.findOne(
                QBcWiccContractInfo.bcWiccContractInfo.active.eq(1)
                        ?: throw BizException(com.waykichain.chain.commons.biz.dict.ErrorCode.PARAM_ERROR))
        return bcWiccContractInfo

    }

    override fun getByAddress(contractAddress: String?) : BcWiccContractInfo {
        val bcWiccContractInfo = bcWiccContractInfoRepository.findOne(
                QBcWiccContractInfo.bcWiccContractInfo.contractAddress.eq(contractAddress)
                ?: throw BizException(com.waykichain.chain.commons.biz.dict.ErrorCode.PARAM_ERROR))
        return bcWiccContractInfo
    }

    override fun getById(id:Long): BcWiccContractInfo? {
        return bcWiccContractInfoRepository.findOne(id)
    }

    override fun save(bcWiccContractInfo: BcWiccContractInfo): BcWiccContractInfo {
        return bcWiccContractInfoRepository.saveAndFlush(bcWiccContractInfo)
    }

    @Autowired lateinit var bcWiccContractInfoRepository: BcWiccContractInfoRepository
}
