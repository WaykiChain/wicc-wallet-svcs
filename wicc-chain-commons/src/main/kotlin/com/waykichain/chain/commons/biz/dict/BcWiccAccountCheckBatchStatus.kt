package com.waykichain.chain.commons.biz.dict

/**
 * Created by Joss on 2018/9/11.
 */
enum class BcWiccAccountCheckBatchStatus(val type: Int, val msg: String) {
    SUBMIT              (100,  "已提交/待处理"),
    WAITING             (200,  "处理中"),
    FINISHED             (300,  "处理完成"),
    AJUSTING           (400,  "矫正中"),
    AJUETED            (880,  "矫正完成")
}