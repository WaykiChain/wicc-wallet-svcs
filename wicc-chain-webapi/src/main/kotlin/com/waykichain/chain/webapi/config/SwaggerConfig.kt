package com.waykichain.chain.webapi.config

import com.waykichain.chain.commons.biz.env.swagger.Environment
import io.swagger.annotations.ApiOperation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/8 21:21
 * @Description: $des
 */
@Configuration
open class SwaggerConfig {
    @Bean
    open fun createRestApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .host(Environment.SWAGGER_BAAS_HOST_V1)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.waykichain.chain.webapi.controller"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation::class.java))
                .paths(PathSelectors.any())
                .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title(Environment.SWAGGER_BAAS_TITLE_V1)
                .description(Environment.SWAGGER_BAAS_DESCRIPTION)
                .termsOfServiceUrl(Environment.SWAGGER_BAAS_TERMSOFSERVICEURL)
//                .contact(Contact("WaykiChain","https://wiccdev.org/","biz@waykichainhk.com"))
                .version(Environment.SWAGGER_BAAS_VERSION_V1)
                .build()
    }
}
