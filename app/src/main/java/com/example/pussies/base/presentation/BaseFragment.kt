package com.example.pussies.base.presentation

import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

}