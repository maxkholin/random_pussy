package com.example.pussies.di

import androidx.lifecycle.ViewModel
import com.example.pussies.presentation.viewmodel.DetailedInfoViewModel
import com.example.pussies.presentation.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailedInfoViewModel::class)
    fun bindDetailedInfoViewModel(viewModel: DetailedInfoViewModel): ViewModel

}