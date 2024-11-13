package com.example.pussies.domain

interface PussyRepository {

    suspend fun loadOnePussyData(): Pussy

    suspend fun insertPussyToFavorite(pussy: Pussy)

    suspend fun deletePussyFromFavorite(pussyId: String)

    suspend fun getAllPussiesFromFavorite(): List<Pussy>
}