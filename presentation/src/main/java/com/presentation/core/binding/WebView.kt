package com.presentation.core.binding

import android.os.Build
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.BindingAdapter

@BindingAdapter("enableCookie")
fun WebView.bindCookie(enable: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, enable)
    else CookieManager.getInstance().setAcceptCookie(enable)
}

@BindingAdapter("enableJs")
fun WebView.bindJs(enable: Boolean) {
    settings.javaScriptEnabled = enable
    settings.javaScriptCanOpenWindowsAutomatically = enable
}

@BindingAdapter("contentAccessAllow")
fun WebView.bindContentAccess(enable: Boolean) {
    settings.domStorageEnabled = enable
    settings.allowFileAccess = enable
    settings.allowFileAccessFromFileURLs = enable
    settings.allowUniversalAccessFromFileURLs = enable
    settings.allowContentAccess = enable
}

@BindingAdapter("url")
fun WebView.bindUrl(url: String) {
    loadUrl(url)
}

@BindingAdapter("webClient")
fun WebView.bindWebClient(client: WebViewClient) {
    webViewClient = client
}