package com.presentation.glossary

import androidx.recyclerview.widget.RecyclerView
import com.interactors.glossary.ItemWord
import com.presentation.databinding.ItemWordBinding

class WordHolder(
    private val binding: ItemWordBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemWord) {
        binding.item = item
    }
}