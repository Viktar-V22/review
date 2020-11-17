package com.data.base.db

import androidx.room.TypeConverter

class ListStringConverter {

    private companion object {
        private const val SEPARATOR = ":;:;"
    }

    @TypeConverter
    fun fromList(list: List<String>) = list.joinToString(SEPARATOR)

    @TypeConverter
    fun toList(value: String) = if (value.isEmpty()) emptyList() else value.split(SEPARATOR)
}