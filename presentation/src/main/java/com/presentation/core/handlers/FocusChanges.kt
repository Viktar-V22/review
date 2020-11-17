package com.presentation.core.handlers

import android.view.View
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.filter

@ExperimentalCoroutinesApi
fun View.focused() = focusChanges().filter { it }

@ExperimentalCoroutinesApi
fun View.focusChanges() = callbackFlow {
    val listener = View.OnFocusChangeListener { _, hasFocus -> offer(hasFocus) }
    onFocusChangeListener = listener
    awaitClose { onFocusChangeListener = null }
}.conflate()

/*
fun View.focused(): Observable<Unit> = focusChanges().filter { it }.map { Unit }

fun View.focusChanges(): Observable<Boolean> {
    return FocusObservable(this)
}

private class FocusObservable(private val view: View) : Observable<Boolean>() {

    override fun subscribeActual(observer: Observer<in Boolean>) {
        val listener = FocusListener(view, observer)
        observer.onSubscribe(listener)
        view.onFocusChangeListener = listener
    }
}

private class FocusListener(
    private val view: View,
    private val observer: Observer<in Boolean>
) : MainThreadDisposable(), View.OnFocusChangeListener {

    override fun onFocusChange(v: View, hasFocus: Boolean) {
        if (!isDisposed) observer.onNext(hasFocus)
    }

    override fun onDispose() {
        view.onFocusChangeListener = null
    }
}*/
