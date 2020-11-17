package com.presentation.constructor

import androidx.recyclerview.widget.RecyclerView
import com.interactors.constructor.ItemLetter
import com.presentation.databinding.ItemLetterBinding

class LetterHolder(private val binding: ItemLetterBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemLetter) {
        binding.item = item
    }
}