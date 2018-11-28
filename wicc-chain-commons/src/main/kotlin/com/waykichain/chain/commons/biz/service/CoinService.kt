package com.waykichain.chain.commons.biz.service

import com.waykichain.chain.biz.domain.Coin

/**
 * @Author: Joss
 * @Date 2018/03/29 下午8:30
 */
interface CoinService {


    fun getBySymbol(symbol:String): Coin?

    fun getById(id: Long): Coin?
}