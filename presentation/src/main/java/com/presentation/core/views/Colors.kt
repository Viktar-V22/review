package com.presentation.core.views

import com.presentation.R

object BaseColor : ColorAttr {
    override fun colorAttr() = R.attr.baseColor
}

object BaseDisabledColor : ColorAttr {
    override fun colorAttr() = R.attr.baseDisabledColor
}

object ReverseColor : ColorAttr {
    override fun colorAttr() = R.attr.reverseColor
}

object ReverseDisabledColor : ColorAttr {
    override fun colorAttr() = R.attr.reverseColor
}

object WhiteColor : ColorResource {
    override fun colorRes() = android.R.color.white
}