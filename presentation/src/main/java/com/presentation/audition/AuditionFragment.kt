package com.presentation.audition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.core.common.Result.NONE
import com.core.common.lazyNone
import com.interactors.audition.Interactor
import com.interactors.audition.NavTarget
import com.interactors.audition.NavTarget.Back
import com.presentation.core.Navigation
import com.presentation.core.extensions.getViewModel
import com.presentation.core.extensions.hideKeyboard
import com.presentation.core.extensions.requireBinding
import com.presentation.core.extensions.window
import com.presentation.core.handlers.editorActions
import com.presentation.core.handlers.safeClicks
import com.presentation.core.keyboard.KeyboardDetector
import com.presentation.databinding.FragmentAuditionBinding
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class AuditionFragment : Fragment() {

    @Inject
    lateinit var interactor: Lazy<Interactor>

    @Inject
    lateinit var form: Lazy<AuditionForm>

    @Inject
    lateinit var navigation: Navigation<NavTarget>

    private val viewModel by lazyNone {
        getViewModel { AuditionViewModel(form.get(), interactor.get()) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentAuditionBinding.inflate(inflater).also {
        it.lifecycleOwner = viewLifecycleOwner
        it.form = viewModel.form
    }.root

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        KeyboardDetector.with(window())
            .detect()
            .onEach { viewModel.form.keyboard(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        val binding = requireBinding<FragmentAuditionBinding>()

        listOf(
            binding.ivSpeech.safeClicks(),
            binding.ivSpeechLarge.safeClicks()
        )
            .merge()
            .onEach { viewModel.speech() }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        listOf(
            binding.fab.safeClicks(),
            binding.etAnswer.editorActions(EditorInfo.IME_ACTION_DONE)
        )
            .merge()
            .onEach {
                hideKeyboard()
                if (viewModel.form.completed.get() && viewModel.form.result.get() != NONE)
                    navigation.navigate(Back) else viewModel.clickFab()
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}