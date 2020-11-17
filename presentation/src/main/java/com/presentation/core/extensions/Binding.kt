package com.presentation.core.extensions

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

fun <T : Observable> T.addOnPropertyChanged(callback: (T) -> Unit): Observable.OnPropertyChangedCallback {
    val changeCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(observable: Observable, i: Int) = callback(observable as T)
    }
    addOnPropertyChangedCallback(changeCallback)
    return changeCallback
}

fun ObservableField<String>.safeGet(defValue: String = ""): String = get() ?: defValue

fun ObservableField<String>.clear() = set("")

fun ObservableField<String>.get(defValue: String = ""): String = get() ?: defValue

fun <T> ObservableField<T>.safeGet(def: T): T = get() ?: def

fun ObservableBoolean.reverse() = set(!get())

fun ObservableBoolean.force(value: Boolean) {
    if (get() == value) reverse()
    set(value)
}

fun <T> ObservableField<T>.force(value: T, intermediate: T? = null) {
    if (get() == value) set(intermediate)
    set(value)
}