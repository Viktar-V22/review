package com.interactors.home

enum class TrainCount(val count: Int) {
    TEN(10),
    TWENTY(20),
    THIRTY(30),
    ALL(-1);

    companion object {
        fun valueOf(value: Int) = values().firstOrNull { it.count == value } ?: ALL
    }
}