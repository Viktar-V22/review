package com.data.base.db

import androidx.room.TypeConverter

class SetStringConverter {

    private companion object {
        private const val SEPARATOR = ":;:;"
    }

    @TypeConverter
    fun fromList(list: Set<String>) = list.joinToString(SEPARATOR)

    @TypeConverter
    fun toList(value: String) = if (value.isEmpty()) emptySet() else value.split(SEPARATOR).toSet()
}