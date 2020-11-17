package com.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.core.common.lazyNone
import com.interactors.home.Interactor
import com.interactors.home.NavTarget
import com.interactors.home.toNavTarget
import com.presentation.R
import com.presentation.core.Navigation
import com.presentation.core.extensions.getViewModel
import com.presentation.core.extensions.requireBinding
import com.presentation.core.extensions.screenWidth
import com.presentation.databinding.FragmentHomeBinding
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

class HomeFragment : Fragment() {

    @Inject
    lateinit var interactor: Lazy<Interactor>

    @Inject
    lateinit var form: Lazy<HomeForm>

    @Inject
    lateinit var trains: Provider<TrainsAdapter>

    @Inject
    lateinit var navigation: Navigation<NavTarget>

    private val trainSize by lazyNone {
        resources.getDimensionPixelSize(R.dimen.trains_size) +
                resources.getDimensionPixelSize(R.dimen.def_half_margin) * 2
    }

    private val viewModel by lazyNone {
        getViewModel { HomeViewModel(form.get(), interactor.get()) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(inflater).also {
        it.lifecycleOwner = viewLifecycleOwner
        it.form = viewModel.form
        it.trains = trains.get()
    }.root

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = requireBinding<FragmentHomeBinding>()
        binding.trains!!.trains.onEach { navigation.navigate(it.train.toNavTarget()) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.trains!!.menu.onEach { viewModel.menuClick(it.first, it.second) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.form.columns.set(requireContext().screenWidth() / trainSize)
    }
}