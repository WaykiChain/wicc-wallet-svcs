package com.waykichain.chain.commons.biz.utils

import java.math.BigDecimal
import java.util.*

/**
 * Created by Joss
 * @date 2018/7/20
 */
object WiccUtils {

    fun convert(number:Long): BigDecimal {
       return number.toBigDecimal().divide(BigDecimal(100_000_000)).setScale(8)
    }

    fun longToString(number:Long): String {
        return number.toBigDecimal().divide(BigDecimal(100_000_000)).setScale(8).stripTrailingZeros().toPlainString()
    }

    fun getGmt0(ts:Long): Date {
        return Date((ts - 8*60*60) *1000)
    }
}