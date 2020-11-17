package com.presentation.core.binding

import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED

@BindingAdapter("lock")
fun DrawerLayout.bindLock(lock: Boolean) {
    setDrawerLockMode(if (lock) LOCK_MODE_LOCKED_CLOSED else LOCK_MODE_UNLOCKED)
}