package com.darkos.oauth.common

import java.lang.ref.WeakReference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class WeakRefList<T: Any>: ReadWriteProperty<Any, List<T>> {
    private var refList: List<WeakReference<T>> = emptyList()

    private fun checkData(){
        refList = refList.filter { it.get() != null }
        refList = refList.distinct()
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: List<T>) {
        refList = refList + value.map { WeakReference(it) }
        checkData()
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): List<T> {
        return refList.mapNotNull { it.get() }.also {
            checkData()
        }
    }
}

fun <T: Any>weakList() = WeakRefList<T>()
