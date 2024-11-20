package com.example.pussies.base.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PussyInfoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPussy(pussyDbModel: PussyDbModel)

    @Query("DELETE FROM pussies WHERE id = :pussyId")
    suspend fun deletePussy(pussyId: String)

    @Query("SELECT * FROM pussies WHERE id = :pussyId")
    fun getPussyById(pussyId: String): LiveData<PussyDbModel>

    @Query("SELECT * FROM pussies")
    fun getAllPussies(): LiveData<List<PussyDbModel>>
}