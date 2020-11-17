package com.boundaries.irregulars

interface IrregularsRepository {

    suspend fun prepare()

    suspend fun verbs(): List<Irregular>
}