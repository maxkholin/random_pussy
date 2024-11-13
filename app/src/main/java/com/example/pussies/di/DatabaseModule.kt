package com.example.pussies.di

import android.app.Application
import com.example.pussies.data.database.AppDatabase
import com.example.pussies.data.database.PussyInfoDao
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @Provides
    fun provideCoinInfoDao(
        application: Application
    ): PussyInfoDao {
        return AppDatabase.getInstance(application).pussyInfoDao()
    }
}