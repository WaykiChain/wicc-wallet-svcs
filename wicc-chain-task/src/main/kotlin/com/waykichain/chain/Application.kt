package com.waykichain.chain

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableCaching
@EnableSwagger2
@EnableAutoConfiguration
open class Application

fun main(args: Array<String>) {
    com.waykichain.chain.commons.biz.env.Environment.init()
    System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2")
    SpringApplication.run(Application::class.java, *args)
}
