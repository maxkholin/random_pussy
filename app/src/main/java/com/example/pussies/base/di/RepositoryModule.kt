package com.example.pussies.base.di

import com.example.pussies.base.data.repository.PussyRepositoryImpl
import com.example.pussies.base.domain.PussyRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindPussyRepository(impl: PussyRepositoryImpl): PussyRepository
}