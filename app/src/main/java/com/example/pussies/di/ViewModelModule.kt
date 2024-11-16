package com.example.pussies.di

import androidx.lifecycle.ViewModel
import com.example.pussies.presentation.viewmodel.DetailedInfoViewModel
import com.example.pussies.presentation.viewmodel.FavoritesViewModel
import com.example.pussies.presentation.viewmodel.RandomPussyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RandomPussyViewModel::class)
    fun bindRandomPussyViewModel(viewModel: RandomPussyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailedInfoViewModel::class)
    fun bindDetailedInfoViewModel(viewModel: DetailedInfoViewModel): ViewModel

}