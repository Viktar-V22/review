package com.core.common

data class Page<T>(
    val items: List<T>,
    val page: Int,
    val totalPages: Int
)