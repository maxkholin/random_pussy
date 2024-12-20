package com.example.pussies.di

import android.app.Application
import com.example.pussies.presentation.MainActivity
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

    fun inject(activity: MainActivity)
}
