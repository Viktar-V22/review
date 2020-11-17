package com.presentation.core.extensions

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.registerOnPageChangeCallback(
    stateChanged: (state: Int) -> Unit = {},
    pageScrolled: (position: Int, offset: Float, offsetPixels: Int) -> Unit = { _, _, _ -> },
    pageSelected: (position: Int) -> Unit = {}
): ViewPager2.OnPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {

    override fun onPageScrollStateChanged(state: Int) = stateChanged(state)

    override fun onPageScrolled(position: Int, offset: Float, offsetPx: Int) {
        pageScrolled(position, offset, offsetPx)
    }

    override fun onPageSelected(position: Int) = pageSelected(position)

}.apply { registerOnPageChangeCallback(this) }