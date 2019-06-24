package com.waykichain.chain.dict

enum class TransactionType(val code: Int, val type: String) {

    ALL(0, "ALL"),
    REWARD_TX(1, "REWARD_TX"),
    REG_ACCT_TX(2, "REG_ACCT_TX"),
    COMMON_TX(3, "COMMON_TX"),
    CONTRACT_TX(4, "CONTRACT_TX"),
    REG_APP_TX(5, "REG_APP_TX"),
    DELEGATE_TX(6, "DELEGATE_TX");

    companion object {
        fun parseTransactionTypeToCode(type: String): Int? {
            TransactionType.values().forEach {
                if (type.equals(it.type)) {
                    return it.code
                }
            }
            return null
        }
    }


}
