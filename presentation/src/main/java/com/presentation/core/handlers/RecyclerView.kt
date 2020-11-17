package com.presentation.core.handlers

/*
fun RecyclerView.firstCompleteVisiblePosition(): Observable<Int> {
    return Observable.create<Int> { emitter ->
        val manager = layoutManager as LinearLayoutManager

        val listener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val position = manager.findFirstCompletelyVisibleItemPosition()
                emitter.onNext(position)
            }
        }

        addOnScrollListener(listener)
        emitter.setCancellable { removeOnScrollListener(listener) }
    }.distinctUntilChanged()
}*/
