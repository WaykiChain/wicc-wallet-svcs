package com.waykichain.chain.commons.biz.dict

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/7/2 21:07
 *
 * @Description:    $des
 *
 */
enum class ErrorCodeDefiniteDict(val code: Int, val msg: String, val originalCode: Int, val originalMsg: String, val type: Int) {

    OFFLINE_TX_SIGN_ERROR (3100, "Signature is error.", -4, "sendrawtx error: bad-signscript-check", 0),
    OFFLINE_TX_DECADDRESS_ERROR (3110, "Incorrect destination account.", -4, "sendrawtx error: bad-read-accountdb", 0),
    OFFLINE_TX_SEND_ACCOUNT_NOT_EXIST (3120, "Sender account not exist.", -4, "sendrawtx error: bad-getaccount", 0),

    OFFLINE_TX_BALANCE_NOT_ENOUGH (3300, "Insufficient balance", -4, "sendrawtx error: operate-minus-account-failed", 0),
    OFFLINE_TX_BALANCE_NOT_ENOUGH_CONTRACT (3310, "Insufficient balance", -4, "sendrawtx error: operate-account-failed", 0),
    OFFLINE_TX_FEE_TOO_SMALL (3320, "Fee is not enough.", -4, "sendrawtx error: bad-regtx-fee-toosmall", 0),

    OFFLINE_TX_ACCOUNT_ALREADY_REGISTER (3210, "Do not register again.", -4, "sendrawtx error: duplicate-register-account", 0),
    OFFLINE_TX_DUPLICATE_SUBMIT(3220, "Please do not submit tx duplicately.", -4, "sendrawtx error: tx-duplicate-confirmed", 0),

    OFFLINE_TX_CONTRACT_RUN_ERROR (3410, "", -4, "sendrawtx error: run-script-error:", 1),

    OFFLINE_TX_NOT_LATEST_HEIGHT (3500, "This height is not newest.", -4, "sendrawtx error: tx-invalid-height", 0)


}

enum class  ErrorCodeDefiniteType(val type: Int, val msg: String) {
    COMPLETE_REPLACEMENT (0, "全部替换"),
    PARTIAL_REPLACEMENT_FROM_FRONT (1, "匹配开头，部分替换")
}