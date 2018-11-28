package com.waykichain.chain.commons.biz.service

/**
 * @Author: Joss
 * @Date 2018/03/29 下午8:30
 */
interface SysConfigService {


    fun getByName(name: String): String

}