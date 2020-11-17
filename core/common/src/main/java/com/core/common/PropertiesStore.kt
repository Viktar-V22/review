package com.core.common

interface PropertiesStore {

    fun getBoolean(key: String, defValue: Boolean = false): Boolean

    fun putBoolean(key: String, value: Boolean)

    fun getInt(key: String, defValue: Int = Int.MIN_VALUE): Int

    fun putInt(key: String, value: Int)

    fun getDouble(key: String, defValue: Double = Double.MIN_VALUE): Double

    fun putDouble(key: String, value: Double)

    fun getFloat(key: String, defValue: Float = Float.MIN_VALUE): Float

    fun putFloat(key: String, value: Float)

    fun getLong(key: String, defValue: Long = Long.MIN_VALUE): Long

    fun putLong(key: String, value: Long)

    fun getString(key: String, defValue: String = ""): String

    fun putString(key: String, value: String)

    fun putSet(key: String, value: Set<String>)

    fun getSet(key: String, defValue: Set<String> = emptySet()): Set<String>

    fun contains(key: String): Boolean

    fun remove(key: String)

    fun clearAll()
}