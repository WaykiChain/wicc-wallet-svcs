package com.waykichain.chain.contract.wusd.domain

object WusdConstants {

    const val WUSD_CONTRACT_SYSTYPE = "f0"

    const val WUSD_CONTRACT_TYPE_SET_ALIVE:String = "01"
    const val WUSD_CONTRACT_TYPE_SET_GODDESS:String = "10"
    const val WUSD_CONTRACT_TYPE_SET_ADMIN:String = "11"
    const val WUSD_CONTRACT_TYPE_SET_SWITCH:String = "12"
    const val WUSD_CONTRACT_TYPE_SET_EXCHANGE_RATE:String = "13"
    const val WUSD_CONTRACT_TYPE_EXCHANGE_TOKEN:String =  "14"
    const val WUSD_CONTRACT_TYPE_EXCHAGE_WICC_BY_ADMIN:String = "15"

    const val WUSD_CONTRACT_TYPE_TRANSFER_TOKEN_By_User:String = "16"
    const val WUSD_CONTRACT_TYPE_TRANSFER_TOKEN_WITH_FEE_BY_ADMIN:String = "17"
    const val WUSD_CONTRACT_TYPE_FREEZE_TOKEN:String = "18"
    const val WUSD_CONTRACT_TYPE_UNFREEZ_TOKEN:String = "19"

    const val WUSD_CONTRACT_TYPE_CREATE_GAME:String = "21"
    const val WUSD_CONTRACT_TYPE_CREATE_GAME_AND_BET:String = "22"
    const val WUSD_CONTRACT_TYPE_UPDATE_GAME:String = "23"
    const val WUSD_CONTRACT_TYPE_UPDATE_GAME_AND_BET:String = "24"
    const val WUSD_CONTRACT_TYPE_BET_BY_ADMIN:String = "25"
    const val WUSD_CONTRACT_TYPE_BATCH_BET_BY_ADMIN:String = "26"
    const val WUSD_CONTRACT_TYPE_CLOSE_GAME_BY_ADMIN:String = "27"

    const val WUSD_CONTRACT_TYPE_SEND_PRIZE_ACCORDING_GAME:String = "28"
    const val WUSD_CONTRACT_TYPE_BATCH_SEND_PRIZE:String = "29"

    const val WUSD_CONTRACT_LIFE_DEAD:String = "00"
    const val WUSD_CONTRACT_LIFE_ALIVE:String = "01"

    const val WUSD_CONTRACT_SWITCH_VALUE_OPEN:String = "00"
    const val WUSD_CONTRACT_SWITCH_VALUE_CLOSE:String = "01"

    const val WUSD_CONTRACT_ADMIN_TYPE_SUPERVISOR:String = "02"
    const val WUSD_CONTRACT_ADMIN_TYPE_TOKEN_ADMIN:String = "03"
    const val WUSD_CONTRACT_ADMIN_TYPE_GAME_ADMIN:String = "04"
    const val WUSD_CONTRACT_ADMIN_TYPE_LOTT_ADMIN:String = "05"

}