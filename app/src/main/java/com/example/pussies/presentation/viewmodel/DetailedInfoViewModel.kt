package com.example.pussies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pussies.domain.usecases.PussyUseCasesFacade
import javax.inject.Inject

class DetailedInfoViewModel @Inject constructor(
    private val useCases: PussyUseCasesFacade
) : ViewModel() {

}