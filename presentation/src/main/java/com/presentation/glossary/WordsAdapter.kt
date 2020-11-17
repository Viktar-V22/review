package com.presentation.glossary

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.interactors.glossary.ItemWord
import com.presentation.core.extensions.inflater
import com.presentation.core.extensions.safeClicks
import com.presentation.core.views.BaseAdapter
import com.presentation.databinding.ItemWordBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.asFlow
import java.util.*
import javax.inject.Inject

class WordsAdapter @Inject constructor() : BaseAdapter<ItemWord>() {

    override val data = ArrayList<ItemWord>()

    @ExperimentalCoroutinesApi
    private val checkChannel = BroadcastChannel<ItemWord>(1)

    @ExperimentalCoroutinesApi
    private val speechChannel = BroadcastChannel<ItemWord>(1)

    @FlowPreview
    @ExperimentalCoroutinesApi
    val check = checkChannel.asFlow()

    @FlowPreview
    @ExperimentalCoroutinesApi
    val speech = speechChannel.safeClicks()

    @ExperimentalCoroutinesApi
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemWordBinding.inflate(parent.inflater(), parent, false)
        binding.checkbox.setOnClickListener { checkChannel.offer(binding.item!!) }
        binding.ivSpeech.setOnClickListener { speechChannel.offer(binding.item!!) }
        return WordHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        (holder as WordHolder).bind(getItem(pos))
    }

    override fun diffCallback(
        old: List<ItemWord>,
        actual: List<ItemWord>
    ): DiffUtil.Callback = WordsDiff(old, actual)
}