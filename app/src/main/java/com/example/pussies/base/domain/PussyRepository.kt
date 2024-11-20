package com.example.pussies.base.domain

import androidx.lifecycle.LiveData
import com.example.pussies.base.data.database.PussyDbModel

interface PussyRepository {

    suspend fun loadOnePussyData(): Pussy

    suspend fun insertPussyToFavorite(pussy: Pussy)

    suspend fun deletePussyFromFavorite(pussyId: String)

    fun getPussyById(pussyId: String): PussyDbModel?

    fun getAllPussiesFromFavorite(): LiveData<List<Pussy>>
}