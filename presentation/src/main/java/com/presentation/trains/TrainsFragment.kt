package com.presentation.trains

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.core.common.lazyNone
import com.interactors.trains.Interactor
import com.interactors.trains.NavTarget
import com.interactors.trains.toNavTarget
import com.presentation.R
import com.presentation.core.HasNavBottom
import com.presentation.core.Navigation
import com.presentation.core.binding.bindOffset
import com.presentation.core.extensions.getViewModel
import com.presentation.core.extensions.requireBinding
import com.presentation.core.extensions.screenWidth
import com.presentation.databinding.FragmentTrainsBinding
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

class TrainsFragment : Fragment(), HasNavBottom {

    @Inject
    lateinit var interactor: Lazy<Interactor>

    @Inject
    lateinit var form: Lazy<TrainsForm>

    @Inject
    lateinit var trains: Provider<TrainsAdapter>

    @Inject
    lateinit var navigation: Navigation<NavTarget>

    private val trainSize by lazyNone { resources.getDimensionPixelSize(R.dimen.trains_size) }

    private val viewModel by lazyNone {
        getViewModel { TrainsViewModel(form.get(), interactor.get()) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTrainsBinding.inflate(inflater).also {
        it.lifecycleOwner = viewLifecycleOwner
        it.form = viewModel.form
        it.trains = trains.get()
    }.root

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = requireBinding<FragmentTrainsBinding>()
        binding.trains!!.trains.onEach { navigation.navigate(it.train.toNavTarget()) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.trains!!.menu.onEach { viewModel.menuClick(it.first, it.second) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        val screenWidth = requireContext().screenWidth()
        val columns = screenWidth / trainSize
        viewModel.form.columns.set(columns)

        val offset = ((screenWidth - columns * trainSize) / (columns + 1)).toFloat()
        binding.rcvTrains.bindOffset(offset, offset)
    }

    override fun navBottom() = requireBinding<FragmentTrainsBinding>().navBottom
}