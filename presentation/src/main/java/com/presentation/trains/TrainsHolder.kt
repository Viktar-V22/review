package com.presentation.trains

import androidx.recyclerview.widget.RecyclerView
import com.interactors.trains.ItemTrain
import com.presentation.databinding.ItemTrainBinding

class TrainsHolder(private val binding: ItemTrainBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemTrain) {
        binding.item = item
    }
}