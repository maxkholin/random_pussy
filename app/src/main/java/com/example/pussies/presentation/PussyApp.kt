package com.example.pussies.presentation

import android.app.Application
import com.example.pussies.di.DaggerAppComponent


class PussyApp: Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}