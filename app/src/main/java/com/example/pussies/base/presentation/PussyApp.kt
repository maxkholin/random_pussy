package com.example.pussies.base.presentation

import android.app.Application
import com.example.pussies.base.di.DaggerAppComponent

class PussyApp : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}