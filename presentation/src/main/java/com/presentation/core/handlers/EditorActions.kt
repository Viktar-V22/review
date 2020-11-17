package com.presentation.core.handlers

import android.widget.TextView
import com.core.common.AlwaysTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.filter

@ExperimentalCoroutinesApi
fun TextView.editorActions(actionId: Int, handled: (Int) -> Boolean = AlwaysTrue): Flow<Int> {
    return editorActions(handled).filter { it == actionId }
}

@ExperimentalCoroutinesApi
fun TextView.editorActions(handled: (Int) -> Boolean = AlwaysTrue) = callbackFlow {
    val listener = TextView.OnEditorActionListener { _, actionId, _ ->
        val isHandled = handled(actionId)
        if (isHandled) offer(actionId)
        isHandled
    }
    setOnEditorActionListener(listener)
    awaitClose { setOnEditorActionListener(null) }
}.conflate()

/*
fun TextView.editorActions(handled: (Int) -> Boolean = AlwaysTrue): Observable<Int> {
    return EditorActionsObservable(this, handled)
}

private class EditorActionsObservable(
    private val view: TextView,
    private val handled: (Int) -> Boolean
) : Observable<Int>() {

    override fun subscribeActual(observer: Observer<in Int>) {
        val listener = EditorActionsListener(view, observer, handled)
        observer.onSubscribe(listener)
        view.setOnEditorActionListener(listener)
    }
}

private class EditorActionsListener(
    private val view: TextView,
    private val observer: Observer<in Int>,
    private val handled: (Int) -> Boolean
) : MainThreadDisposable(), TextView.OnEditorActionListener {

    override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
        return try {
            if (!isDisposed && handled(actionId)) {
                observer.onNext(actionId)
                true
            } else false
        } catch (ex: Exception) {
            observer.onError(ex)
            dispose()
            false
        }
    }

    override fun onDispose() {
        view.setOnEditorActionListener(null)
    }
}*/