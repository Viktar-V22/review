package com.presentation.irregular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.core.common.lazyNone
import com.interactors.irregular.InputField.*
import com.interactors.irregular.Interactor
import com.interactors.irregular.NavTarget
import com.presentation.core.Navigation
import com.presentation.core.extensions.getViewModel
import com.presentation.core.extensions.hideKeyboard
import com.presentation.core.extensions.requireBinding
import com.presentation.core.extensions.window
import com.presentation.core.handlers.editorActions
import com.presentation.core.handlers.safeClicks
import com.presentation.core.keyboard.KeyboardDetector
import com.presentation.databinding.FragmentIrregularBinding
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class IrregularFragment : Fragment() {

    @Inject
    lateinit var interactor: Lazy<Interactor>

    @Inject
    lateinit var form: Lazy<IrregularForm>

    @Inject
    lateinit var navigation: Navigation<NavTarget>

    private val viewModel by lazyNone {
        getViewModel { IrregularViewModel(form.get(), interactor.get()) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentIrregularBinding.inflate(inflater).also {
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

        val binding = requireBinding<FragmentIrregularBinding>()

        listOf(
            binding.fab.safeClicks(),
            binding.etPastParticiple.editorActions(IME_ACTION_DONE)
        )
            .merge()
            .onEach {
                hideKeyboard()
                if (viewModel.form.completed.get()) navigation.navigate(NavTarget.Back) else
                    viewModel.clickFab()
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        listOf(binding.speechInfinitive.safeClicks().map { INFINITIVE },
            binding.speechPast.safeClicks().map { PAST },
            binding.speechPastParticiple.safeClicks().map { PAST_PARTICIPLE })
            .merge()
            .onEach { viewModel.speech(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}