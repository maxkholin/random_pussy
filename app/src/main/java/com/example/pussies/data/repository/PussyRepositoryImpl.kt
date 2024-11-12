package com.example.pussies.data.repository

import com.example.pussies.data.database.PussyInfoDao
import com.example.pussies.data.mapper.PussyMapper
import com.example.pussies.data.network.ApiService
import com.example.pussies.domain.Pussy
import com.example.pussies.domain.PussyRepository
import javax.inject.Inject

class PussyRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val pussyInfoDao: PussyInfoDao,
    private val mapper: PussyMapper
) : PussyRepository {


    override suspend fun loadOnePussyData(): Pussy {
        val pussyData = apiService.loadPussyData()
        val pussyDto = pussyData.firstOrNull() ?: throw Exception("Empty response")
        val isFavorite = pussyInfoDao.getPussyById(pussyDto.id) != null
        return mapper.mapDtoToDomain(pussyDto, isFavorite)
    }

    override suspend fun insertPussyToFavorite(pussy: Pussy) {
        val pussyDbModel = mapper.mapDomainToDb(pussy)
        pussyInfoDao.insertPussy(pussyDbModel)
    }

    override suspend fun deletePussyFromFavorite(pussyId: String) {
        pussyInfoDao.deletePussy(pussyId)
    }

    override suspend fun getAllPussiesFromFavorite(): List<Pussy> {
        val list = pussyInfoDao.getAllPussies()
        return mapper.mapListDbToDomain(list)
    }


}