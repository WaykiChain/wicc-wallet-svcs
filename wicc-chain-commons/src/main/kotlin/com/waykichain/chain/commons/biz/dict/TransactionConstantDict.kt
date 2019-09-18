package com.waykichain.chain.commons.biz.dict

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/8 14:05
 *
 * @Description:    $des
 *
 */
enum class TransactionConstantDict(val numValue: Long, val value: String, val message: String) {

    SENDTOADDRESS_DEFAULT_FEE(10000, "10000", "转账（sendtoaddress）默认手续费"),
    SENDCONTRACT_DEFAULT_FEE(100000, "100000", "管理员发起合约交易默认手续费"),
    SENDCONTRACT_DEFAULT_ADMIN_TYPE(100, "10000", "管理员发起合约交易默认管理员类型"),
    TEST_MONEY_10(10000_0000_0, "1000000000", "获取测试币100wicc"),
    TEST_MONEY_1000(10000_0000_000, "100000000000", "获取测试币1000wicc"),
    TEST_MONEY_10000(10000_0000_0000, "1000000000000", "获取测试币10000wicc")
}