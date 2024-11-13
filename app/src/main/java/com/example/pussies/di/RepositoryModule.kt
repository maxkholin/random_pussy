package com.example.pussies.di

import com.example.pussies.data.repository.PussyRepositoryImpl
import com.example.pussies.domain.PussyRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindPussyRepository(impl: PussyRepositoryImpl): PussyRepository
}