package com.waykichain.chain.po

import com.waykichain.chain.vo.TransactionVO
import java.io.Serializable

class TransactionsVO: Serializable {
    var transactions:List<TransactionVO> ?=null
    var currentHeight:Int ?= null
}