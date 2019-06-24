package com.waykichain.chain.commons.biz.dict

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/5/9 17:17
 *
 * @Description:    合约账户类型
 *
 */

enum class ContractDataType(val code: String, val message: String){

    CONTRACT_DATA_STRING("STRING", "字符串"),
    CONTRACT_DATA_NUMBER("NUMBER", "数字"),
    CONTRACT_DATA_HEX("HEX", "16进制")
}