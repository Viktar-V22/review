package com.boundaries.irregulars

data class Irregular(
    val id: Long,
    val ru: Set<String>,
    val infinitive: Verb,
    val past: Verb,
    val pp: Verb
)