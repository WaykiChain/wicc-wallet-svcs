package com.waykichain.chain.commons.biz.configuration

import com.waykichain.bet.commons.biz.exception.BizException
import com.waykichain.chain.commons.biz.service.SysConfigService
import org.bouncycastle.asn1.x500.style.RFC4519Style.name
import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner
import org.reflections.util.FilterBuilder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component


@Component
class SysConfigLoadManager {
    var logger = LoggerFactory.getLogger(com.waykichain.chain.commons.biz.configuration.SysConfigLoadManager::class.java)

    @Autowired
    lateinit var context: ConfigurableApplicationContext

    @Autowired
    lateinit var sysConfigService: SysConfigService

    fun init() {

        val envClz = Reflections("com.waykichain", SubTypesScanner(false), FilterBuilder().include(".*\\.Environment.class"))

        val envClz1 = envClz.getSubTypesOf(Object::class.java)
        envClz1.forEach { handle(it) }

        val envClz2 = envClz.getSubTypesOf(com.waykichain.chain.commons.biz.env.BaseEnv::class.java)
        envClz2.forEach { handle(it) }
    }

    private fun handle(clz: Class<out Any>) {
        clz.declaredFields.forEach {
            try {
                val value = sysConfigService.getByName(it.name)
                logger.info("config init ==========> name=${it.name} value=$value")
                val type = it.type.toString()//得到此属性的类型
                var obj: Any? = null
                if (type.endsWith("String")) {
                    obj = value
                } else if (type.endsWith("int") || type.endsWith("Integer")) {
                    obj = value.toInt()
                } else if (type.endsWith("boolean")) {
                    obj = value.toBoolean()
                } else if (type.endsWith("long")) {
                    obj = value.toLong()
                }

                it.set(clz, obj)
                logger.info("${it.name}:$obj")
            } catch (e: BizException) {
                logger.warn("-------------------------------SysConfigLoadManager2 load end:${e.message}")
                return@forEach
            } catch (e: NoSuchFieldException) {
                logger.warn("-------------------------------SysConfigLoadManager2 loan exception : ${e.message}")
                return@forEach
            } catch (e: Exception) {
                logger.error("-------------------------------SysConfigLoadManager2 load end:${e.message}", e)
                System.exit(SpringApplication.exit(context))
            }
        }
    }
}

