package com.example.pussies.base.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.pussies.base.data.database.PussyInfoDao
import com.example.pussies.base.data.mapper.PussyMapper
import com.example.pussies.base.data.network.ApiService
import com.example.pussies.base.domain.Pussy
import com.example.pussies.base.domain.PussyRepository
import javax.inject.Inject

private const val TAG = "Pussy RepositoryImpl"

class PussyRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val pussyInfoDao: PussyInfoDao,
    private val mapper: PussyMapper
) : PussyRepository {

    override suspend fun loadOnePussyData(): Pussy {
        return try {
            val pussyData = apiService.loadPussyData()
            val pussyDto = pussyData.first()
            val isFavorite = pussyInfoDao.getPussyById(pussyDto.id) != null
            mapper.mapDtoToDomain(pussyDto, isFavorite)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertPussyToFavorite(pussy: Pussy) {
        val pussyDbModel = mapper.mapDomainToDb(pussy)
        pussyInfoDao.insertPussy(pussyDbModel)
    }

    override suspend fun deletePussyFromFavorite(pussyId: String) {
        pussyInfoDao.deletePussy(pussyId)
    }

    override suspend fun checkPussyById(pussyId: String): Boolean {
        return pussyInfoDao.getPussyById(pussyId) != null
    }

    override fun getAllPussiesFromFavorite(): LiveData<List<Pussy>> =
        pussyInfoDao.getAllPussies()
            .map { list ->
                list.map(mapper::mapDbToDomain)
            }
}