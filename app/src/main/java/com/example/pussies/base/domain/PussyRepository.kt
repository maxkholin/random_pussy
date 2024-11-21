package com.example.pussies.base.domain

import androidx.lifecycle.LiveData

interface PussyRepository {

    suspend fun loadOnePussyData(): Pussy

    suspend fun insertPussyToFavorite(pussy: Pussy)

    suspend fun deletePussyFromFavorite(pussyId: String)

    suspend fun checkPussyById(pussyId: String): Boolean

    fun getAllPussiesFromFavorite(): LiveData<List<Pussy>>
}