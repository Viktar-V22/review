package com.presentation.core.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FabHideOnScrollBehavior(
    context: Context,
    attrs: AttributeSet
) : FloatingActionButton.Behavior(context, attrs) {

    private val hideListener = object : FloatingActionButton.OnVisibilityChangedListener() {
        override fun onHidden(fab: FloatingActionButton) {
            super.onHidden(fab)
            fab.visibility = View.INVISIBLE
        }
    }

    override fun onStartNestedScroll(
        coordinator: CoordinatorLayout,
        child: FloatingActionButton,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ) = axes == ViewCompat.SCROLL_AXIS_VERTICAL

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )

        when {
            dyConsumed > 0 && child.visibility == View.VISIBLE -> child.hide(hideListener)
            dyConsumed < 0 && child.visibility != View.VISIBLE -> child.show()
        }
    }
}