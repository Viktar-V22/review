package com.lexicon.core.navigation

import android.content.Context
import android.os.Bundle
import androidx.navigation.NavController
import java.lang.ref.WeakReference

class Navigator(private val closeApp: CloseApp) : Router, NavBinder {

    private var controller = WeakReference<NavController>(null)
    private var context = WeakReference<Context>(null)

    override fun bind(context: Context, controller: NavController) {
        this.context = WeakReference(context)
        this.controller = WeakReference(controller)
    }

    override fun unbind() {
        context.clear()
        controller.clear()
    }

    override fun navigate(action: Int, params: Bundle) {
        controller.get()?.navigate(action, params)
    }

    override fun closeApp() {
        context.get()?.let { closeApp.close(it) }
    }

    override fun navigateUp() {
        controller.get()?.navigateUp()
    }
}