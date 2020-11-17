package com.data.core

import android.content.SharedPreferences
import com.core.common.PropertiesStore
import java.lang.Double.*

class PreferenceStore(private val prefs: SharedPreferences) : PropertiesStore {

    private val editor: SharedPreferences.Editor by lazy { prefs.edit() }

    override fun getBoolean(key: String, defValue: Boolean) = prefs.getBoolean(key, defValue)

    override fun putBoolean(key: String, value: Boolean) = editor.run {
        putBoolean(key, value)
        apply()
    }

    override fun getInt(key: String, defValue: Int) = prefs.getInt(key, defValue)

    override fun putInt(key: String, value: Int) = editor.run { putInt(key, value); apply() }

    override fun getDouble(key: String, defValue: Double): Double {
        return longBitsToDouble(prefs.getLong(key, doubleToLongBits(defValue)))
    }

    override fun putDouble(key: String, value: Double) = editor.run {
        putLong(key, doubleToRawLongBits(value))
        apply()
    }

    override fun getFloat(key: String, defValue: Float) = prefs.getFloat(key, defValue)

    override fun putFloat(key: String, value: Float) = editor.run { putFloat(key, value); apply() }

    override fun getLong(key: String, defValue: Long) = prefs.getLong(key, defValue)

    override fun putLong(key: String, value: Long) = editor.run { putLong(key, value); apply() }

    override fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue) ?: defValue
    }

    override fun putString(key: String, value: String) = editor.run {
        putString(key, value)
        apply()
    }

    override fun putSet(key: String, value: Set<String>) = editor.run {
        putStringSet(key, value)
        apply()
    }

    override fun getSet(key: String, defValue: Set<String>): Set<String> {
        return prefs.getStringSet(key, defValue) as Set<String>
    }

    override fun contains(key: String): Boolean {
        return prefs.contains(key)
    }

    override fun remove(key: String) = editor.run { remove(key); apply() }

    override fun clearAll() = editor.run { clear(); apply() }
}