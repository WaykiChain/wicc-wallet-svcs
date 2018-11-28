package com.waykichain.chain.config

import com.waykichain.chain.filter.CustomerServletFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import javax.servlet.Filter

@Configuration
open class CommonMvcConfig : WebMvcConfigurerAdapter() {


    @Bean
    open fun customerServletFilter(): Filter {

        return CustomerServletFilter()
    }

}