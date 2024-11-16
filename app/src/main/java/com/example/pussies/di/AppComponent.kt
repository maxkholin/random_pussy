package com.example.pussies.di

import android.app.Application
import com.example.pussies.presentation.fragments.DetailedInfoFragment
import com.example.pussies.presentation.fragments.FavoritePussiesFragment
import com.example.pussies.presentation.fragments.RandomPussyFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DatabaseModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(fragment: RandomPussyFragment)
    fun inject(fragment: FavoritePussiesFragment)
    fun inject(fragment: DetailedInfoFragment)
}
