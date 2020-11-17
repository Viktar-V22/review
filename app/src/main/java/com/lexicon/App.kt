package com.lexicon

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.request.CachePolicy
import coil.util.CoilUtils
import com.lexicon.di.AndroidModule
import com.lexicon.di.AppComponent
import com.lexicon.di.DaggerAppComponent
import com.lexicon.di.HasAppComponent
import okhttp3.OkHttpClient

class App : Application(), HasAppComponent {

    companion object {
        private lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = buildComponent()
        setupCoil()
    }

    override fun component() = component

    private fun buildComponent() = DaggerAppComponent.builder()
        .androidModule(AndroidModule(this))
        .build()

    private fun setupCoil() {
        val imageLoader = ImageLoader.Builder(this)
            .availableMemoryPercentage(0.3)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(applicationContext))
                    .build()
            }
            .build()
        Coil.setImageLoader(imageLoader)
    }
}