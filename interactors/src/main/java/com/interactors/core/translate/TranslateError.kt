package com.interactors.core.translate

sealed class TranslateError(val text: String) {
    class UnknownLang(text: String) : TranslateError(text)
    class CanNotTranslate(text: String) : TranslateError(text)
    class Unknown(text: String, val ex: Exception) : TranslateError(text)
}
