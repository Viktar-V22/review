package com.presentation.home

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.interactors.home.ItemTrain
import javax.inject.Inject

class HomeForm @Inject constructor() {

    val columns = ObservableInt()
    val trains = ObservableField<List<ItemTrain>>()
}