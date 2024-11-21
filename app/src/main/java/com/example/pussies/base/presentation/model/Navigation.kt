package com.example.pussies.base.presentation.model

import androidx.navigation.NavDirections

sealed class Navigation {

    class To(val direction: NavDirections) : Navigation()

    data object Pop : Navigation()
}