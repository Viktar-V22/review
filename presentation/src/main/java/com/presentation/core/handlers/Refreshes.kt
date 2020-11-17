package com.presentation.core.handlers

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

@ExperimentalCoroutinesApi
fun SwipeRefreshLayout.refreshes() = callbackFlow {
    val listener = SwipeRefreshLayout.OnRefreshListener { offer(Unit) }
    setOnRefreshListener(listener)
    awaitClose { setOnRefreshListener(null) }
}.conflate()

/*
fun SwipeRefreshLayout.refreshes(): Observable<Unit> {
    return RefreshesObservable(this)
}

private class RefreshesObservable(private val view: SwipeRefreshLayout) : Observable<Unit>() {

    override fun subscribeActual(observer: Observer<in Unit>) {
        val listener = RefreshListener(view, observer)
        observer.onSubscribe(listener)
        view.setOnRefreshListener(listener)
    }
}

private class RefreshListener(
    private val view: SwipeRefreshLayout,
    private val observer: Observer<in Unit>
) : MainThreadDisposable(), SwipeRefreshLayout.OnRefreshListener {

    override fun onRefresh() {
        if (!isDisposed) observer.onNext(Unit)
    }

    override fun onDispose() {
        view.setOnRefreshListener(null)
    }
}*/
