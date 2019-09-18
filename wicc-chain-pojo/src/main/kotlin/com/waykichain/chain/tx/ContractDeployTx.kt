package com.waykichain.coin.wicc.vo.tx

/**
 *  Created by yehuan on 2019/7/24
 */

class ContractDeployTx: BaseTx(){

    var signature: String? = null
    var contract_code: String? = null
    var contract_memo: String? = null
}