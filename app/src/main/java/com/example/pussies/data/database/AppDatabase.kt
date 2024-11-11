package com.example.pussies.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PussyDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pussyInfoDao(): PussyInfoDao

    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "pussy.db"

        fun getInstance(context: Context): AppDatabase {
            if (db == null) {
                synchronized(this) {
                    if (db == null) {
                        db = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            DB_NAME
                        ).build()
                    }
                }
            }
            return db!!
        }
    }
}