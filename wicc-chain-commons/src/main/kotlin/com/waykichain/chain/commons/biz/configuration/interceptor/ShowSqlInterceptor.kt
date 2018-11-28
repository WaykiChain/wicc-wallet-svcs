package com.waykichain.bet.commons.biz.interceptor

import org.apache.ibatis.executor.Executor
import org.apache.ibatis.mapping.BoundSql
import org.apache.ibatis.mapping.MappedStatement
import org.apache.ibatis.plugin.*
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.ResultHandler
import org.apache.ibatis.session.RowBounds
import org.slf4j.LoggerFactory
import java.text.DateFormat
import java.util.*
import java.util.regex.Matcher

@Intercepts(
        Signature(type = Executor::class, method = "update", args = arrayOf(MappedStatement::class, Any::class)),
        Signature(type = Executor::class, method = "query", args = arrayOf(MappedStatement::class, Any::class, RowBounds::class, ResultHandler::class))
)
open class ShowSqlInterceptor : Interceptor {

    private val LOGGER = LoggerFactory.getLogger(ShowSqlInterceptor::class.java)

    override fun intercept(invocation: Invocation?): Any? {
        val mappedStatement = invocation!!.getArgs()[0] as MappedStatement
        var parameter: Any? = null
        if (invocation.getArgs().size > 1) {
            parameter = invocation.getArgs()[1]
        }

        val sqlId = mappedStatement.id
//        if (sqlId != null && !sqlId.endsWith("updateByPrimaryKey") && !sqlId.endsWith("updateByExample")) {
//            parameter = ignoreSqlFields(parameter)
//        }

        val boundSql = mappedStatement.getBoundSql(parameter)

        val configuration = mappedStatement.configuration
        var returnValue: Any? = null
        val start = System.currentTimeMillis()
        try {
            returnValue = invocation.proceed()
        } finally {
            val end = System.currentTimeMillis()
            val time = end - start
            if (time > 1) {
                val sql = getSql(configuration, boundSql, sqlId, time)
                LOGGER.info("\n   " + sql + "\n")
            }
        }
        return returnValue
    }

    override fun setProperties(properties: Properties?) {

    }

    override fun plugin(target: Any?): Any {
        return Plugin.wrap(target, this)
    }

    private fun getSql(configuration: Configuration, boundSql: BoundSql,
                       sqlId: String, time: Long): String {
        val sql = showSql(configuration, boundSql)
        val str = StringBuilder(100)
        str.append(sql)
        str.append("; - ")
        str.append(time)
        str.append("ms")
        str.append(":")
        str.append(sqlId)
        return str.toString()
    }

    private fun showSql(configuration: Configuration, boundSql: BoundSql): String {
        val parameterObject = boundSql.parameterObject
        val parameterMappings = boundSql.parameterMappings
        var sql = boundSql.sql.replace("[\\s]+".toRegex(), " ")
        if (parameterMappings.size > 0 && parameterObject != null) {
            val typeHandlerRegistry = configuration.typeHandlerRegistry
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.javaClass)) {
                sql = sql.replaceFirst("\\?".toRegex(), getParameterValue(parameterObject))

            } else {
                val metaObject = configuration.newMetaObject(parameterObject)
                for (parameterMapping in parameterMappings) {
                    val propertyName = parameterMapping.property
                    if (metaObject.hasGetter(propertyName)) {
                        val obj = metaObject.getValue(propertyName)
                        sql = sql.replaceFirst("\\?".toRegex(), getParameterValue(obj))
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        val obj = boundSql.getAdditionalParameter(propertyName)
                        sql = sql.replaceFirst("\\?".toRegex(), getParameterValue(obj))
                    }
                }
            }
        }
        return sql
    }

    private fun getParameterValue(obj: Any?): String {
        val value: String
        if (obj is String) {
            value = "'" + obj.toString() + "'"
        } else if (obj is Date) {
            val formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
                    DateFormat.DEFAULT, Locale.CHINA)
            value = "'" + formatter.format(obj as Date?) + "'"
        } else {
            if (obj != null) {
                value = obj.toString()
            } else {
                value = ""
            }

        }
        return Matcher.quoteReplacement(value)
    }

    fun ignoreSqlFields(parameter: Any?): Any? {

        if (null == parameter) {
            return null
        }

        val clazz = parameter.javaClass
        try {
            val field = clazz.getDeclaredField("updatedAt")
            field.isAccessible = true
            field.set(parameter, null)
        } catch (e: NoSuchFieldException) {
            //LOGGER.debug(" NoSuchFieldException for clazz[" + clazz + "]");
            val supperCls = clazz.superclass
            try {
                val field = supperCls.getDeclaredField("updateAt")
                field.isAccessible = true
                field.set(parameter, null)
            } catch (e1: NoSuchFieldException) {
                //LOGGER.debug(" NoSuchFieldException for supperCls[" + supperCls + "]");
            } catch (e1: IllegalAccessException) {
                LOGGER.debug(" IllegalAccessException supperCls exception : " + e.toString())
            }

        } catch (e: IllegalAccessException) {
            LOGGER.debug(" IllegalAccessException exception : " + e.toString())
        }

        return parameter
    }
}