package com.lexicon.di.navigation

import com.lexicon.core.navigation.*
import com.lexicon.di.main.MainScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface NavigationModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @MainScope
        fun provideNavigator(closeApp: CloseApp): Navigator = Navigator(closeApp)
    }

    @Binds
    @MainScope
    fun provideNavBinder(navigator: Navigator): NavBinder

    @Binds
    @MainScope
    fun providerRouter(navigator: Navigator): Router

    @Binds
    @MainScope
    fun provideCloseApp(close: CloseAppImpl): CloseApp

    @Binds
    @MainScope
    fun provideNavBack(back: NavBackImpl): NavBack
}