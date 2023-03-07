package com.anrayus.apirequest.ext

import java.util.stream.Collectors
import kotlin.reflect.KProperty1

/**
 * 用以获取list中对象的某一属性并组成list
 * @param property 需要的字段
 *
 */
fun <T,E> List<T>.getPropertyList(property : KProperty1<T, E>): MutableList<E> {
    return this.stream().map(property).collect(Collectors.toList())
}