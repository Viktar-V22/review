package com.presentation.core.extensions

import android.view.View
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.PopupMenu

fun View.inflater() = context.inflater()

fun View.popupMenu(menuRes: Int): PopupMenu {
    return PopupMenu(context, this).apply { inflate(menuRes) }
}

// TODO workaround for showing icons for menu
fun View.showPopupMenuWithIcons(menu: PopupMenu) {
    val helper = MenuPopupHelper(context, menu.menu as MenuBuilder, this)
    helper.setForceShowIcon(true)
    helper.show()
}
