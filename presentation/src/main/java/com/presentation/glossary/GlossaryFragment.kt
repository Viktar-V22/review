package com.presentation.glossary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.core.common.lazyNone
import com.interactors.glossary.Interactor
import com.interactors.glossary.NavTarget
import com.presentation.core.HasNavBottom
import com.presentation.core.Navigation
import com.presentation.core.extensions.getViewModel
import com.presentation.core.extensions.requireBinding
import com.presentation.core.handlers.safeClicks
import com.presentation.databinding.FragmentGlossaryBinding
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

class GlossaryFragment : Fragment(), HasNavBottom {

    @Inject
    lateinit var interactor: Lazy<Interactor>

    @Inject
    lateinit var form: Lazy<GlossaryForm>

    @Inject
    lateinit var navigation: Navigation<NavTarget>

    @Inject
    lateinit var words: Provider<WordsAdapter>

    private val viewModel by lazyNone {
        getViewModel { GlossaryViewModel(form.get(), interactor.get()) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentGlossaryBinding.inflate(inflater).also {
        it.lifecycleOwner = viewLifecycleOwner
        it.form = viewModel.form
        it.words = words.get()
    }.root

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refreshItems()

        val binding = requireBinding<FragmentGlossaryBinding>()
        binding.words!!.check.onEach { viewModel.check(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.words!!.speech.onEach { viewModel.speech(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.fab.safeClicks().onEach { viewModel.fab() }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun navBottom() = requireBinding<FragmentGlossaryBinding>().navBottom
}