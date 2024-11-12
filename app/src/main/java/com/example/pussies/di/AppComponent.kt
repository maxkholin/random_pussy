package com.example.pussies.di

import com.example.pussies.presentation.MainActivity
import dagger.Component

@Component(
    modules = [
        DatabaseModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)
}