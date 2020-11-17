package com.presentation.core.handlers

import android.view.View
import com.presentation.core.extensions.throttleFirst
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

@ExperimentalCoroutinesApi
fun View.safeClicks() = clicks().throttleFirst(500)

@ExperimentalCoroutinesApi
fun View.clicks() = callbackFlow {
    val listener = View.OnClickListener { offer(Unit) }
    setOnClickListener(listener)
    awaitClose { setOnClickListener(null) }
}.conflate()

/*
fun View.safeClicks(): Observable<Unit> = clicks()
    .throttleFirst(500, TimeUnit.MILLISECONDS)

fun View.clicks(): Observable<Unit> = ClicksObservable(this)

private class ClicksObservable(private val view: View) : Observable<Unit>() {

    override fun subscribeActual(observer: Observer<in Unit>) {
        val listener = ClickListener(view, observer)
        observer.onSubscribe(listener)
        view.setOnClickListener(listener)
    }
}

private class ClickListener(
    private val view: View,
    private val observer: Observer<in Unit>
) : MainThreadDisposable(), View.OnClickListener {

    override fun onClick(v: View) {
        if (!isDisposed) observer.onNext(Unit)
    }

    override fun onDispose() {
        view.setOnClickListener(null)
    }
}*/