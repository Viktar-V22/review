package com.presentation.core

interface Navigation<T> {

    fun navigate(target: T)
}