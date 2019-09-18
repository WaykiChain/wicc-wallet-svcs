package com.waykichain.chain.commons.biz.utils

import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.math.BigDecimal
import java.util.HashMap

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/7/31 11:47
 * @Description:
 */
object MyBeanUtils {


    fun copyProperties(source: Any, target: Any) {
        copyPropertiesDo(source, target, false)
    }

    /**
     * 只将target未赋值的数据进行赋值
     * @param source
     * @param target
     */
    fun copyNotNullProperties(source: Any, target: Any) {
        copyPropertiesDo(source, target, true)
    }

    private fun copyPropertiesDo(source: Any?, target: Any?, isNotNull: Boolean = false) {

        if (source == null || target == null) {
            return
        }
        try {
            val srcFieldMap = getAssignableFieldsMap(source)
            val targetFieldMap = getAssignableFieldsMap(target)
            for (srcFieldName in srcFieldMap.keys) {
                val srcField = srcFieldMap[srcFieldName]
                if (srcField?.get(source) == null) {
                    continue
                }
                // 变量名需要相同
                if (!targetFieldMap.keys.contains(srcFieldName) && !targetFieldMap.keys.contains(srcFieldName.replace("_", ""))) {
                    continue
                }
                val targetField = (targetFieldMap[srcFieldName] ?: targetFieldMap[srcFieldName.replace("_", "")]) ?: continue
                if (isNotNull ) continue
                //BigDecimal to String
                if (srcField.type == BigDecimal::class.java && targetField.type == String::class.java) {
                    val b = srcField.get(source) as BigDecimal
                    targetField.set(target, b.stripTrailingZeros().toPlainString())
                    continue
                }
                // 类型需要相同
                if (srcField.type != targetField.type) {
                    continue
                }

                targetField.set(target, srcField.get(source))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return
    }


    private fun getAssignableFieldsMap(obj: Any?): Map<String, Field> {
        if (obj == null) {
            return HashMap()
        }
        val fieldMap = HashMap<String, Field>()
        var objClass = obj.javaClass
        var fileds = arrayListOf<Field>()
        fileds.addAll(objClass.declaredFields)
        if (objClass.superclass != null) fileds.addAll(objClass.superclass.declaredFields)
        for (field in fileds) {
            // 过滤不需要拷贝的属性
            if (Modifier.isStatic(field.modifiers) || Modifier.isFinal(field.modifiers))continue
            field.isAccessible = true
            fieldMap[field.name] = field
        }
        return fieldMap
    }

}
