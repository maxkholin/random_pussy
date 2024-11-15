package com.example.pussies.domain

import androidx.lifecycle.LiveData

interface PussyRepository {

    suspend fun loadOnePussyData(): Pussy

    suspend fun insertPussyToFavorite(pussy: Pussy)

    suspend fun deletePussyFromFavorite(pussyId: String)

    fun getAllPussiesFromFavorite(): LiveData<List<Pussy>>
}