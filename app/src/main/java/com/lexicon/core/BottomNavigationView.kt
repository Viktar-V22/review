package com.lexicon.core

import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.setupWithNavController(controller: NavController, rootId: Int) {
    setupWithNavController(controller)
    setOnNavigationItemSelectedListener { item ->
        if (!item.isChecked) {
            when (item.itemId) {
                rootId -> controller.navigateUp()
                else -> NavigationUI.onNavDestinationSelected(item, controller)
            }
        }
        true
    }
}