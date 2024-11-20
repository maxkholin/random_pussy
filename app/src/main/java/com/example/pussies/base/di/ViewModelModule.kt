package com.example.pussies.base.di

import androidx.lifecycle.ViewModel
import com.example.pussies.detailedInfo.DetailedInfoViewModel
import com.example.pussies.favorites.presentation.FavoritesViewModel
import com.example.pussies.randomPussy.RandomPussyViewModel
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