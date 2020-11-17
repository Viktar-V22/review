package com.core.common

enum class Result {
    POSITIVE,
    NEGATIVE,
    NONE;

    companion object {
        fun fromBoolean(result: Boolean?) = when (result) {
            true -> POSITIVE
            false -> NEGATIVE
            null -> NONE
        }
    }
}

fun Result.isPositive() = this == Result.POSITIVE

fun Result.isNegative() = this == Result.NEGATIVE

fun Result.toBoolean() = when (this) {
    Result.POSITIVE -> true
    Result.NEGATIVE -> false
    Result.NONE -> null
}