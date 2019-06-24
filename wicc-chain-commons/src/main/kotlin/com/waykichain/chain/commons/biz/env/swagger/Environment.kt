package com.waykichain.chain.commons.biz.env.swagger

import com.waykichain.chain.commons.biz.env.BaseEnv
import com.waykichain.chain.commons.biz.env.Environment

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/9 9:49
 *
 * @Description:    $des
 *
 */
object Environment: BaseEnv() {

    @JvmField
    var SWAGGER_BAAS_TITLE_V1 = Environment.env("SWAGGER_BAAS_TITLE_V1", "Baas(Blockchain as a Service) v1 for Mainnet")

    @JvmField
    var SWAGGER_BAAS_VERSION_V1 = Environment.env("SWAGGER_BAAS_VERSION_V1", "v2.0")

    @JvmField
    var SWAGGER_BAAS_HOST_V1 = Environment.env("SWAGGER_BAAS_HOST_V1", "baas.wiccdev.org/v1/api")


    @JvmField
    var SWAGGER_BAAS_HOST = Environment.env("SWAGGER_BAAS_HOST", "baas.wiccdev.org/v2/api")


    @JvmField
    var SWAGGER_BAAS_TITLE = Environment.env("SWAGGER_BAAS_TITLE", "Baas(Blockchain as a Service) v2 for Mainnet")

    @JvmField
    var SWAGGER_BAAS_DESCRIPTION = Environment.env("SWAGGER_BAAS_DESCRIPTION", "Support：https://www.wiccdev.org/book/en/DeveloperHelper/baas.html  \n" +
            "帮助：https://www.wiccdev.org/book/zh-hans/DeveloperHelper/baas.html")

    @JvmField
    var SWAGGER_BAAS_TERMSOFSERVICEURL = Environment.env("SWAGGER_BAAS_TERMSOFSERVICEURL", "https://wiccdev.org/")

    @JvmField
    var SWAGGER_BAAS_VERSION = Environment.env("SWAGGER_BAAS_VERSION", "v2.0")
}