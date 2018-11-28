package com.waykichain.chain.contract.wusd.domain

object WusdConstants {

    const val WUSD_CONTRACT_SYSTYPE = "f0"

    const val WUSD_CONTRACT_TYPE_SET_ADMIN:String = "11"
    const val WUSD_CONTRACT_TYPE_SET_SWITCH:String = "12"
    const val WUSD_CONTRACT_TYPE_SET_EXCHANGE_RATE:String = "13"
    const val WUSD_CONTRACT_TYPE_EXCHANGE_TOKEN:String = "04"
    const val WUSD_CONTRACT_TYPE_EXCHAGE_WICC_BY_ADMIN:String = "14"

    const val WUSD_CONTRACT_TYPE_TRANSFER_TOKEN_By_User:String = "07"
    const val WUSD_CONTRACT_TYPE_TRANSFER_TOKEN_WITH_FEE_BY_ADMIN:String = "21"

    const val WUSD_CONTRACT_TYPE_CREATE_GAME:String = "22"
    const val WUSD_CONTRACT_TYPE_CREATE_GAME_AND_BET:String = "23"
    const val WUSD_CONTRACT_TYPE_UPDATE_GAME:String = "31"
    const val WUSD_CONTRACT_TYPE_UPDATE_GAME_AND_BET:String = "24"
    const val WUSD_CONTRACT_TYPE_BET_BY_ADMIN:String = "25"
    const val WUSD_CONTRACT_TYPE_BATCH_BET_BY_ADMIN:String = "26"
    const val WUSD_CONTRACT_TYPE_CLOSE_GAME_BY_ADMIN:String = "27"
    const val WUSD_CONTRACT_TYPE_SEND_PRIZE_ACCORDING_GAME:String = "32"
    const val WUSD_CONTRACT_TYPE_BATCH_SEND_PRIZE:String = "33"

    const val WUSD_CONTRACT_TYPE_FREEZE_TOKEN:String = "28"
    const val WUSD_CONTRACT_TYPE_UNFREEZ_TOKEN:String = "29"

    const val WUSD_CONTRACT_SWITCH_VALUE_OPEN:String = "01"
    const val WUSD_CONTRACT_SWITCH_VALUE_CLOSE:String = "00"

}