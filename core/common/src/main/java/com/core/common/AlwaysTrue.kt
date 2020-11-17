package com.core.common

object AlwaysTrue : () -> Boolean, (Any) -> Boolean {
    override fun invoke() = true
    override fun invoke(ignored: Any) = true
}