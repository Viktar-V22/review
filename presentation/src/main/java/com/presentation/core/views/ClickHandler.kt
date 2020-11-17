package com.presentation.core.views

abstract class ClickHandler {

    private var lastClickTimestamp = 0L
    private var lastClickHash = -1

    protected fun tryClick(click: () -> Unit, delay: Long = 500L) {
        if (isAllowClick(click.hashCode(), delay)) click.invoke()
    }

    protected fun <T> tryClick(click: (params: T) -> Unit, params: T, delay: Long = 500L) {
        if (isAllowClick(click.hashCode(), delay)) click.invoke(params)
    }

    private fun isAllowClick(hash: Int, delay: Long = 500L): Boolean {
        val currentTimestamp = System.currentTimeMillis()
        val delta = currentTimestamp - lastClickTimestamp

        val isAllow = delta !in 0L..delay || hash != lastClickHash
        if (isAllow) {
            lastClickTimestamp = currentTimestamp
            lastClickHash = hash
        }

        return isAllow
    }
}