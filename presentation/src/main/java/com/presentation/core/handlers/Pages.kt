package com.presentation.core.handlers

import androidx.viewpager2.widget.ViewPager2
import com.presentation.core.extensions.registerOnPageChangeCallback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

@ExperimentalCoroutinesApi
fun ViewPager2.pages() = callbackFlow {
    val pageSelected: (Int) -> Unit = { position -> offer(position) }
    val callback = registerOnPageChangeCallback(pageSelected = pageSelected)
    awaitClose { unregisterOnPageChangeCallback(callback) }
}.conflate()

/*
fun ViewPager2.pages(): Observable<Int> = PagesObservable(this)

private class PagesObservable(private val view: ViewPager2) : Observable<Int>() {

    override fun subscribeActual(observer: Observer<in Int>) {
        val disposable = PageDisposable(view, observer)
        observer.onSubscribe(disposable)
        view.registerOnPageChangeCallback(disposable.listener)
    }
}

private class PageDisposable(
    private val view: ViewPager2,
    private val observer: Observer<in Int>
) : MainThreadDisposable() {

    val listener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            if (!isDisposed) observer.onNext(position)
        }
    }

    override fun onDispose() {
        view.unregisterOnPageChangeCallback(listener)
    }
}*/
