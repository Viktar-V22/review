package com.presentation.constructor

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.interactors.constructor.ItemLetter
import com.presentation.core.extensions.inflater
import com.presentation.core.extensions.safeClicks
import com.presentation.core.views.BaseAdapter
import com.presentation.databinding.ItemLetterBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import java.util.*
import javax.inject.Inject

class LettersAdapter @Inject constructor() : BaseAdapter<ItemLetter>() {

    override val data = ArrayList<ItemLetter>()

    @ExperimentalCoroutinesApi
    private val letterChannel = BroadcastChannel<ItemLetter>(1)

    @FlowPreview
    @ExperimentalCoroutinesApi
    val letters = letterChannel.safeClicks(200)

    @ExperimentalCoroutinesApi
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemLetterBinding.inflate(parent.inflater(), parent, false)
        binding.root.setOnClickListener { letterChannel.offer(binding.item!!) }
        return LetterHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        (holder as LetterHolder).bind(getItem(pos))
    }

    override fun diffCallback(old: List<ItemLetter>, actual: List<ItemLetter>): DiffUtil.Callback {
        return LettersDiff(old, actual)
    }
}