package com.core.common

class Later<T, R>(
    private val source: (T) -> R,
    private val param: T
) : () -> R {

    override fun invoke() = source(param)
}