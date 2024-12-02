package com.example.pussies.base.di

import android.app.Application
import com.example.pussies.detailedInfo.DetailedInfo
import com.example.pussies.favorites.Favorites
import com.example.pussies.randomPussy.RandomPussy
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

    fun inject(fragment: RandomPussy)
    fun inject(fragment: Favorites)
    fun inject(fragment: DetailedInfo)
}
