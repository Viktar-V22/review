package com.data.core.sound.media

object MediaErrorProcessed : (Int, Int) -> Boolean {
    override fun invoke(what: Int, exra: Int) = true
}