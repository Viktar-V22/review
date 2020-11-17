package com.presentation.constructor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.core.common.lazyNone
import com.interactors.constructor.Interactor
import com.interactors.constructor.NavTarget
import com.presentation.R
import com.presentation.core.Navigation
import com.presentation.core.extensions.getViewModel
import com.presentation.core.extensions.requireBinding
import com.presentation.core.extensions.screenWidth
import com.presentation.core.handlers.safeClicks
import com.presentation.databinding.FragmentConstructorBinding
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

class ConstructorFragment : Fragment() {

    @Inject
    lateinit var letters: Provider<LettersAdapter>

    @Inject
    lateinit var interactor: Lazy<Interactor>

    @Inject
    lateinit var form: Lazy<ConstructorForm>

    @Inject
    lateinit var navigation: Navigation<NavTarget>

    private val letterSize by lazyNone {
        resources.getDimensionPixelSize(R.dimen.action_size) +
                resources.getDimensionPixelSize(R.dimen.def_half_margin) * 2
    }

    private val viewModel by lazyNone {
        getViewModel { ConstructorViewModel(form.get(), interactor.get()) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentConstructorBinding.inflate(inflater).also {
        it.lifecycleOwner = viewLifecycleOwner
        it.form = viewModel.form
        it.letters = letters.get()
    }.root

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = requireBinding<FragmentConstructorBinding>()
        binding.letters!!.letters.onEach { viewModel.select(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.fab.safeClicks().onEach {
            if (viewModel.form.complete.get()) navigation.navigate(NavTarget.Back) else
                viewModel.clickFab()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.ivSpeech.safeClicks().onEach { viewModel.speech() }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.form.columns.set(requireContext().screenWidth() / letterSize)
    }
}