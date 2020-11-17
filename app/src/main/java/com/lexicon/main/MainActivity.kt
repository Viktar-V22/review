package com.lexicon.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.core.common.lazyNone
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.lexicon.R
import com.lexicon.core.findNavController
import com.lexicon.core.navigation.NavBack
import com.lexicon.core.navigation.NavBinder
import com.lexicon.core.setupWithNavController
import com.lexicon.databinding.ActivityMainBinding
import com.lexicon.di.HasAppComponent
import com.lexicon.di.main.DaggerMainComponent
import com.lexicon.di.main.MainInjector
import com.presentation.core.HasDrawer
import com.presentation.core.HasNavBottom
import com.presentation.core.HasToolbar
import com.presentation.core.extensions.getViewModel
import com.presentation.core.extensions.hideKeyboard
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navBinder: NavBinder

    @Inject
    lateinit var navBack: NavBack

    private val viewModel: MainViewModel by lazyNone {
        getViewModel {
            val component = DaggerMainComponent.builder()
                .appComponent((applicationContext as HasAppComponent).component())
                .build()

            MainViewModel(MainInjector(), component)
        }
    }

    private val fragmentsCallback = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
            viewModel.inject(f)
        }

        override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
            if (f is NavHostFragment) return
            if (f is HasNavBottom) setupNavBottom(f.navBottom())
            if (f is HasDrawer) setupNavView(f.navView())

            val drawer = if (f is HasDrawer) f.drawer() else null
            if (f is HasToolbar) setupToolbar(f.toolbar(), drawer, navBack.back(f))
        }

        override fun onFragmentStopped(fm: FragmentManager, f: Fragment) = f.hideKeyboard()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.inject(this)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentsCallback, true)

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater).also {
            it.lifecycleOwner = this
        }
        setContentView(binding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        supportFragmentManager.fragments.forEach {
            it.onActivityResult(requestCode, resultCode, data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        supportFragmentManager.fragments.forEach {
            it.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onResume() {
        super.onResume()
        navBinder.bind(this, findNavController(R.id.nav_host_fragment))
    }

    override fun onPause() {
        navBinder.unbind()
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    override fun onDestroy() {
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentsCallback)
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupNavView(navView: NavigationView) {
        navView.setupWithNavController(findNavController(R.id.nav_host_fragment))
    }

    private fun setupToolbar(toolbar: Toolbar, navDrawer: DrawerLayout?, top: Int) {
        val controller = findNavController(R.id.nav_host_fragment)
        toolbar.setupWithNavController(controller, AppBarConfiguration(setOf(top), navDrawer))
    }

    private fun setupNavBottom(navBottom: BottomNavigationView) {
        navBottom.setupWithNavController(findNavController(R.id.nav_host_fragment), R.id.trains)
    }
}