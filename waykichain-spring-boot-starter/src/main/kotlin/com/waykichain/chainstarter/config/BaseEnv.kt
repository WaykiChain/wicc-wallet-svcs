package com.waykichain.chainstarter.config

import org.joda.time.DateTimeZone
import java.util.*

/**
 *  Created by yehuan on 2019/6/4
 */

open class BaseEnv {

    fun env(name: String, value: String): String {
        val envValue = System.getenv(name)
        val env: String
        if (envValue == null) {
            env = value
        } else {
            env = envValue
        }
        return env
    }

    fun init() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"))
        DateTimeZone.setDefault(DateTimeZone.forOffsetHours(8))
    }

    fun env(name: String, value: Int): Int {
        var env: Int
        try {
            val envValue = System.getenv(name)
            if (envValue != null) {
                env = Integer.parseInt(envValue)
            } else {
                env = value
            }
        } catch (e: NumberFormatException) {
            env = value
        }

        return env
    }

    fun env(name: String, value: Long): Long {
        var env: Long
        try {
            val envValue = System.getenv(name)
            if (envValue != null) {
                env = java.lang.Long.parseLong(envValue)
            } else {
                env = value
            }
        } catch (e: NumberFormatException) {
            env = value
        }

        return env
    }

    fun env(name: String, value: Boolean): Boolean {
        var env: Boolean
        try {
            val envValue = System.getenv(name)
            if (envValue != null) {
                env = envValue.toBoolean()
            } else {
                env = value
            }
        } catch (e: Exception) {
            env = value
        }

        return env
    }
}