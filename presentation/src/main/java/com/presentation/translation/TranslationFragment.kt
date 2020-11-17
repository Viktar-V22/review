package com.presentation.translation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.core.common.Language
import com.core.common.lazyNone
import com.interactors.translation.Interactor
import com.interactors.translation.NavTarget
import com.presentation.core.Navigation
import com.presentation.core.extensions.getViewModel
import com.presentation.core.extensions.requireBinding
import com.presentation.core.handlers.safeClicks
import com.presentation.databinding.FragmentTranslationBinding
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

class TranslationFragment : Fragment() {

    companion object {
        const val SOURCE = "source"
    }

    @Inject
    lateinit var translations: Provider<TranslationsAdapter>

    @Inject
    lateinit var interactor: Lazy<Interactor>

    @Inject
    lateinit var form: Lazy<TranslationForm>

    @Inject
    lateinit var navigation: Navigation<NavTarget>

    private val viewModel by lazyNone {
        getViewModel { TranslationViewModel(form.get(), interactor.get()) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTranslationBinding.inflate(inflater).also {
        it.lifecycleOwner = viewLifecycleOwner
        it.translations = translations.get()
        it.form = viewModel.form
    }.root

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.prepare(Language.fromRaw(arguments?.getString(SOURCE)))

        val binding = requireBinding<FragmentTranslationBinding>()
        binding.translations!!.translations.onEach { viewModel.select(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.translations!!.speech.onEach { viewModel.speech(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.ivSpeech.safeClicks().onEach { viewModel.speech() }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.fab.safeClicks().onEach {
            if (viewModel.form.complete.get()) navigation.navigate(NavTarget.Back) else {
                viewModel.clickFab()
                binding.rcvTranslations.let { it.post { it.scrollToPosition(0) } }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}