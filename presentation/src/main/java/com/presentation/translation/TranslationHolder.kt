package com.presentation.translation

import androidx.recyclerview.widget.RecyclerView
import com.interactors.translation.ItemTranslation
import com.presentation.core.views.ReverseColor
import com.presentation.core.views.WhiteColor
import com.presentation.databinding.ItemTranslationBinding

class TranslationHolder(
    private val binding: ItemTranslationBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemTranslation) {
        binding.item = item
        binding.reverseColor = ReverseColor
        binding.whiteColor = WhiteColor
    }
}