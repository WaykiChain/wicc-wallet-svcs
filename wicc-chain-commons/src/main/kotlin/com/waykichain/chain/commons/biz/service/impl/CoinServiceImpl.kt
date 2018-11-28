package com.waykichain.chain.commons.biz.service.impl

import com.waykichain.chain.biz.domain.Coin
import com.waykichain.chain.biz.domain.QCoin
import com.waykichain.chain.commons.biz.repository.mysql.CoinRepository
import com.waykichain.chain.commons.biz.service.CoinService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CoinServiceImpl : CoinService {

    override fun getBySymbol(symbol: String): Coin? {
        return coinRepository.findOne(QCoin.coin.symbol.eq(symbol))
    }

    override fun getById(id: Long): Coin {
        return coinRepository.findOne(id)
    }


    @Autowired
    private lateinit var coinRepository: CoinRepository
}