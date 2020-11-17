package com.presentation.translation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.interactors.translation.ItemTranslation
import com.presentation.core.extensions.inflater
import com.presentation.core.extensions.safeClicks
import com.presentation.core.views.BaseAdapter
import com.presentation.databinding.ItemTranslationBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import java.util.*
import javax.inject.Inject

class TranslationsAdapter @Inject constructor() : BaseAdapter<ItemTranslation>() {

    override val data = ArrayList<ItemTranslation>()

    @ExperimentalCoroutinesApi
    private val translateChannel = BroadcastChannel<ItemTranslation>(1)

    @ExperimentalCoroutinesApi
    private val speechChannel = BroadcastChannel<ItemTranslation>(1)

    @FlowPreview
    @ExperimentalCoroutinesApi
    val translations = translateChannel.safeClicks()

    @FlowPreview
    @ExperimentalCoroutinesApi
    val speech = speechChannel.safeClicks()

    @ExperimentalCoroutinesApi
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemTranslationBinding.inflate(parent.inflater(), parent, false)
        binding.root.setOnClickListener { translateChannel.offer(binding.item!!) }
        binding.ivSpeech.setOnClickListener { speechChannel.offer(binding.item!!) }
        return TranslationHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        (holder as TranslationHolder).bind(getItem(pos))
    }

    override fun diffCallback(
        old: List<ItemTranslation>,
        actual: List<ItemTranslation>
    ): DiffUtil.Callback = TranslationsDiff(old, actual)
}