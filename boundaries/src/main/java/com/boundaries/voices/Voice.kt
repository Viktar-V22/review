package com.boundaries.voices

import com.core.common.Gender

data class Voice(
    val code: String,
    val name: String,
    val rawName: String,
    val gender: Gender,
    val rawGender: Int
)