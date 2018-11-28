package com.waykichain.chain.commons.biz.dict

/**
 * Created by Joss on 2018/9/11.
 */
enum class BcWiccAccountCheckLogStatus(val type: Int, val msg: String) {
    SUBMIT              (100,  "已提交/待处理"),
    WAITING             (400,  "处理中"),
    NORMAL           (800,  "正常状态"),
    DAIL_FINISHED            (880,  "处理完成"),
    CANCEL(880,  "无需处理");
}