package com.waykichain.chain.commons.biz.configuration

import com.alibaba.druid.pool.DruidDataSource
import com.waykichain.bet.commons.biz.interceptor.ShowSqlInterceptor
import com.waykichain.chain.commons.biz.env.Environment
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.sql.SQLException
import javax.sql.DataSource


/**
 * Created by Joss on 2017/7/25.
 */
@Configuration
open class DatasourceConfig {

    private val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)

    /**
     * 添加mysql 拦截器
     */
    @Bean
    open fun configureCustomer(): ConfigurationCustomizer {

        return ConfigurationCustomizer { ShowSqlInterceptor() }
    }

    /**
     * 配置druid 链接池
     *
     */
    @Bean
    open fun dataSource(): DataSource {

        val datasource = DruidDataSource()

        datasource.url = com.waykichain.chain.commons.biz.env.Environment.MYSQL_URL
        datasource.username = com.waykichain.chain.commons.biz.env.Environment.MYSQL_USERNAME
        datasource.password = com.waykichain.chain.commons.biz.env.Environment.MYSQL_PASSWORD
        datasource.driverClassName = com.waykichain.chain.commons.biz.env.Environment.MYSQL_DRIVER

        //configuration
        datasource.initialSize = com.waykichain.chain.commons.biz.env.Environment.MYSQL_INITIALSIZE
        datasource.minIdle = com.waykichain.chain.commons.biz.env.Environment.MYSQL_MIN_IDLE
        datasource.maxActive = com.waykichain.chain.commons.biz.env.Environment.MYSQL_MAX_ACTIVE
        datasource.maxWait = 5000
        datasource.timeBetweenEvictionRunsMillis = 10000
        datasource.minEvictableIdleTimeMillis = 30000
        datasource.isTestWhileIdle = true
        datasource.isTestOnBorrow = false
        datasource.isTestOnReturn = false
        datasource.isPoolPreparedStatements = true
        datasource.maxPoolPreparedStatementPerConnectionSize = 20
        datasource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000")
        datasource.validationQuery = "select now()"

        try {
            datasource.setFilters("stat,wall,log4j")
        } catch (e: SQLException) {
            logger.error("druid configuration initialization filter", e)
        }

        return datasource
    }
}