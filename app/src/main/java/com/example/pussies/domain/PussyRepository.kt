package com.example.pussies.domain

interface PussyRepository {

    fun loadOnePussyData()

    fun insertPussyToFavorite(pussy: Pussy)

    fun deletePussyFromFavorite(pussyId: String)

    fun getDetailedInfoForOneFavoritePussy(pussyId: String): Pussy

    fun getAllPussiesFromFavorite(): List<Pussy>
}