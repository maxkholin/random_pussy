package com.example.pussies.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PussyInfoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoritePussy(pussyDbModel: PussyDbModel)

    @Query("SELECT * FROM pussies")
    fun getAllFavoritePussy(): List<PussyDbModel>

    @Query("SELECT * FROM pussies WHERE id = :pussyId")
    fun getDetailedFavoritePussy(pussyId: String): PussyDbModel

    @Query("DELETE FROM pussies WHERE id = :pussyId")
    fun deleteFavoritePussy(pussyId: String)

}