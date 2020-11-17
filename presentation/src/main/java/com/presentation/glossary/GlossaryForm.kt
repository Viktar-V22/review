package com.presentation.glossary

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.interactors.glossary.ItemWord
import javax.inject.Inject

class GlossaryForm @Inject constructor() {

    val items = ObservableField<List<ItemWord>>()
    val speech = ObservableBoolean()
}