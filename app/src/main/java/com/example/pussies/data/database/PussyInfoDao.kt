package com.example.pussies.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PussyInfoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPussyToFavorite(pussyDbModel: PussyDbModel)

    @Query("DELETE FROM pussies WHERE id = :pussyId")
    fun deletePussyFromFavorite(pussyId: String)

    @Query("SELECT * FROM pussies WHERE id = :pussyId")
    fun getDetailedInfoForOneFavoritePussy(pussyId: String): PussyDbModel

    @Query("SELECT * FROM pussies")
    fun getAllPussiesFromFavorite(): List<PussyDbModel>
}