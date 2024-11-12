package com.example.pussies.di

import android.content.Context
import androidx.room.Room
import com.example.pussies.data.database.AppDatabase
import com.example.pussies.data.database.PussyInfoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "pussy.db"
        ).build()
    }

    @Provides
    fun providePussyInfoDao(database: AppDatabase): PussyInfoDao {
        return database.pussyInfoDao()
    }
}