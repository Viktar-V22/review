package com.presentation.core

import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

interface HasDrawer {

    fun drawer(): DrawerLayout

    fun navView(): NavigationView
}