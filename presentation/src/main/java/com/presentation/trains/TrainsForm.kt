package com.presentation.trains

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.interactors.trains.ItemTrain
import javax.inject.Inject

class TrainsForm @Inject constructor() {

    val columns = ObservableInt()
    val trains = ObservableField<List<ItemTrain>>()
}