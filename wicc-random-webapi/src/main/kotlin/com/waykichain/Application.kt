package com.waykichain

import com.waykichain.chain.commons.biz.env.Environment
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableCaching
@EnableSwagger2
open class Application

fun main(args: Array<String>) {
    Environment.init()
    SpringApplication.run(Application::class.java, *args)
}
