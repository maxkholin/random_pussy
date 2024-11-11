package com.example.pussies.data.repository

import com.example.pussies.data.database.AppDatabase
import com.example.pussies.data.mapper.PussyMapper
import com.example.pussies.data.network.ApiService
import com.example.pussies.domain.Pussy
import com.example.pussies.domain.PussyRepository
import javax.inject.Inject

class PussyRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val database: AppDatabase,
    private val mapper: PussyMapper
): PussyRepository {

    override fun loadOnePussyData() {
        TODO("Not yet implemented")
    }

    override fun insertPussyToFavorite(pussy: Pussy) {
        TODO("Not yet implemented")
    }

    override fun deletePussyFromFavorite(pussyId: String) {
        TODO("Not yet implemented")
    }

    override fun getDetailedInfoForOneFavoritePussy(pussyId: String): Pussy {
        TODO("Not yet implemented")
    }

    override fun getAllPussiesFromFavorite(): List<Pussy> {
        TODO("Not yet implemented")
    }
}