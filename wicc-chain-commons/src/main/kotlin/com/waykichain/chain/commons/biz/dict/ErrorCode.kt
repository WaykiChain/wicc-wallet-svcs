package com.waykichain.chain.commons.biz.dict

/**
 * Created by Joss on 2018/9/11.
 */
enum class ErrorCode(val code: Int, val msg: String) {

    MOBILE_NOT_EXIST                    (1001, "手机号码未注册"),
    SMS_VALIDATION_FAILED               (1010, "短信验证码错误"),
    IMAGE_VALIDATION_FAILED             (1011, "图形验证码错误"),
    URL_TIMEOUT_OR_INVALID              (1012,"链接失效"),
    SMS_UP_TO_LIMIT_FAILED              (1013, "获取验证码过于频繁，请10分钟后再试"),
    EMAIL_SEND_FAILED                   (1014,"邮件发送失败，请确认邮件是否正确"),
    EMAIL_VALIDATION_FAILED             (1015,"邮箱验证码错误"),
    GOOGLE_VALIDATION_FAILED             (1016,"谷歌验证失败"),

    USER_ALREADY_EXIST                  (2001, "用户已经存在"),
    USER_REGISTER_FAIL                  (2002, "用户注册失败，请联系客服处理"),
    USER_AUTH_FAILED                    (2003, "用户名或密码不正确"),
    USER_NOT_LOGIN                      (2004, "用户未登录"),
    USER_LOGIN_EXPIRED                  (2005, "用户长时间未登录"),
    USER_INVALID                        (2006, "非法用户"),
    CHANNEL_INVALID                     (2007, "非法渠道"),
    SIGN_ERROR                          (2008, "非法签名"),
    REFRESH_TOKEN_EXPIRED               (2009, "用户长时间未登录"),
    CHANNEL_NOT_MATCH                   (2010, "用户和渠道不匹配"),
    ACCESS_TOKEN_EXPIRED                (2011, "AccessToken已过期"),
    USER_RESIGNED                       (2012, "当前用户已经离职"),
    KONG_ACCESS_FAILED                  (2013, "访问KONG获取oAuth2 token失败"),
    KONG_REFRESH_FAILED                 (2014, "访问KONG刷新oAuth2 token失败"),
    API_AUTH_NOT_MATCH                  (2015, "无接口访问权限"),
    USER_REGISTER_IN_OLD_SYS            (2016, "系统不给力, 请稍后再试"),
    USER_ACCESS_TOKEN_SAVE_FAILED       (2017, "保存access_token失败"),
    USER_ORGCHART_NOT_EXIST             (2018, "系统用户OrgChart不存在"),
    USER_NOT_EXIST                      (2019, "用户不存在"),
    RULE_ILLEGAL                        (2020, "套餐已售完"),
    USER_LAST_VISITED                   (2021, "用户上次访问时间为空"),
    USER_IN_BLACKLIST                   (2022, "用户黑名单"),
    USER_REGISTER_LOCKED                (2023, "该账户还未验证,请前往邮箱验证"),
    USER_ACCOUNT_ERRO                   (2024, "账户异常，请联系客服"),
    SMS_CODE_SEND_FAILED                (2025,"短信验证码发送失败"),
    INSUFFICIENT_BALANCE                (2026,"用户余额不足，请充值后操作"),
    CUSTOMER_OPERATION_TOO_OFTEN        (2027, "用户操作太频繁，请稍后再试"),
    CUSTOMER_AUTH_SETING_FAILED         (2028, "安全中心设置失败，该操作手机/邮箱检验必须存在一个"),
    CUSTOMER_AUTH_SETING_GA_CLOSE_OPEN  (2029, "用户打来/关闭GA失败,该操作不允许关闭GA校验"),



    PAYMENT_INFO_EXIST                  (3001, "银行卡已经被绑定过"),
    PAYMENT_INFO_NOT_EXIST              (3002, "查询的银行卡信息不存在"),
    PAYMENT_INFO_BIND_ERROR             (3003, "银行卡绑定失败"),
    PAYMENT_INFO_EXCEED_LIMIT           (3004, "银行卡数量达到上限"),
    PAYMENT_INFO_DELETE_NOT_ALLOWED     (3005, "无法删除银行卡"),
    CHOOSE_PAYMENT_INFO_BEFORE_CONFRIM_LOAN_ORDER     (3006, "确认订单前，请先选择一张银行卡"),
    BIND_PAYMENT_INFO_BEFORE_CONFIRM    (3009, "确认订单前，请先绑定银行卡"),

    TOKEN_VALIDATION_FAILED             (4001, "请求参数错误"),
    PARAM_ERROR                         (4002, "请求参数有误"),
    PARAM_INCOMPLETE_ERROR              (4003, "缺失请求参数"),
    PARAM_SIGN_ERROR                    (4004, "参数签名错误"),

    ACTION_AUTH_FAILED                  (5000,"二次验证失败，请重试"),


    RECORD_NOT_EXIST                    (6001, "记录不存在"),
    RECORD_ALREADY_EXIST                (6002, "记录已经存在"),
    RECORD_STATUS_ERROR                 (6003, "当前状态有问题"),
    OPERATION_NOT_ALLOWED               (6004, "用户操作不允许"),
    MATERIAL_NOT_FULFILLED              (6005, "用户资料尚未全部提交"),
    BID_ORDER_RECORD_STATUS_ERROR       (6101, "买单状态被取消"),
    ASK_ORDER_RECORD_STATUS_ERROR       (6102, "卖单状态被取消"),

    /**
     * 用户管理相关
     */
    NRIC_ALREADY_EXIST                  (6201, "身份证号码已经存在"),
    MOBILE_ALREADY_EXIST                (6202, "手机号码已绑定"),
    NRIC_MUTI_ACCOUNT                   (6203, "账号异常，该身份证号注册了多个账号"),
    EMAIL_ALREADY_EXIST                 (6204, "邮箱已绑定"),
    NRIC_IS_NULL                        (6205, "身份证号码不能为空!"),

    USER_SECURITY_OFF_ERROR             (6300,"关闭异常，已关闭"),
    USER_SECURITY_ON_ERROR              (6301,"打开异常，已打开"),

    YITU_OCR_ERROR                      (6400,"照片解析出错，请重新尝试"),

    /**
     * coin
     */
    COIN_ERROR                          (7100,              "币错误"),
    COIN_INACTIVE                       (7101,              "币种下架"),

    /**
     * account
     */
    ACCOUNT_ERROR                       (7200,              "账户错误"),
    CERTIFY_INFO_EXISTER                (7201,              "认证已存在"),
    CERTIFY_INFO_NOT_EXISTER            (7202,              "认证未存在"),
    ILLEGAL_EMIAL_REGISTE               (7203,              "非法邮箱注册"),
    EMAIL_REGISTER_UPDATING             (7204,              "邮箱注册升级中,稍后再试"),
    ACCOUNT_ERROR_MOBILE_SECURITY_NOT_EXISTER (7205,              "账户未绑定手机"),
    ACCOUNT_ERROR_DEPOSIT_NOT_EXISTER   (7206,              "请进行任意币种的充币"),
    ACCOUNT_ERROR_TRADE_NOT_EXISTER     (7207,              "请进行任意币种的交易或参与娱乐挖矿"),
    ACCOUNT_ERROR_BASE_INFO_NOT_EXISTER (7208,              "个人信息未认证"),
    ACCOUNT_ERROR_IDCARD_NOT_EXISTER    (7209,              "身份证未上传"),
    ACCOUNT_ERROR_VOICE_NOT_AUTH        (7210,              "账户未进行语音认证"),

    /**
     * block chain
     */
    BLOCK_CHAIN_ERROR                   (7300,              "链相关错误"),


    CREATE_ORDER_FILED                  (8001,"创建订单失败,请重试"),
    CREATE_BALANCE_NOT_ENOUGH           (8101,"钱包金额不够"),
    PRODUCT_OFFSHELF                    (8111,"产品已下架"),
    VOL_TOO_LITTLE                      (8121,"下单数量太小"),
    VOL_TOO_MUCH                        (8122,"下单数量太大"),
    ORDER_CANCEL_FAIL                   (8170,"订单撤单失败"),
    ORDER_ALREADY_FINISHED              (8180,"订单已经完成"),
    WITHDRAW_TODAY_QUOTA_IS_FULL        (8220, "提现额度已满"),
    WITHDRAW_NEED_DEPOSITED             (8221, "不满足提币条件,请联系客服"),
    WITHDRAW_COUNT_IS_OVER              (8200, "提币数量过大"),
    ORDER_ONT_EXISTER                   (8190,"订单不存在"),
    CREATE_ORDER_COUNT_PRICE_MIN        (8210,"下单数量为0或价格为0"),
    ORDER_PROCESSING                    (8211,"有订单处理中"),
    CREATE_ORDER_PRICE_OVER             (8212,"该笔挂单近期可能不会成交,请确认挂单数据后下单"),


    TRADE_ORDER_CANCELED                (8220,"订单已取消"),

    ALLOC_REWARD_COUNT_OVER(8221,"送币金额过大"),

    INVALID_GAMIE                       (8300,"游戏不存在"),
    INSUFFICIENT_PAID_AMOUNT            (8301,"在遥远的某处，有人在你之前够买了Key，它涨价了"),
    GAME_IS_UNAVAILABLE                 (8302,"游戏当前状态无法购买 Key"),

    SYS_INTERNAL_ERROR                  (9000, "系统不给力，请稍后再试"),
    RPC_RESPONSE_IS_NULL                (9001, "rpc返回数据为空"),
    UUID_IS_USED                        (9002, "uuid已被使用"),
    GET_BLOCK_HEIGHT_FAIL               (9003, "获取区块高度失败"),



    /**
     * app_release
     */
    APPLY_PLATFORM_ILLEGAL              (10010, "非法的申请平台"),
    FILE_UPLOAD_ERROR                   (12000, "文件上传失败"),

    SECURITY_GOOGLE_AUTH_ERROR          (11000,"谷歌验证绑定,请生成密钥"),

    /**
     * openApi相关ERROR
     */
    API_PARAM_ERROR                         (20001,"参数有误"),
    API_PARAM_SIGN_ERROR                    (20002,"签名错误"),
    API_PARAM_EXPIRED                       (20003,"请求过期"),
    API_ACCESS_KEY_ERROR                    (20004,"access_key异常"),

    /**
     * get_game
     */
    BET_GAME_CONFIG_ERROR                   (30000,"游戏配置错误");


    companion object {

        private val map = com.waykichain.chain.commons.biz.dict.ErrorCode.values().associateBy  { errorCode: com.waykichain.chain.commons.biz.dict.ErrorCode -> errorCode.code }

        fun getByCode(code: Int) = com.waykichain.chain.commons.biz.dict.ErrorCode.Companion.map[code]

        fun getMessage(code: Int): String {
            val result = com.waykichain.chain.commons.biz.dict.ErrorCode.Companion.map[code] ?: return ""
            return result.msg
        }
    }

}