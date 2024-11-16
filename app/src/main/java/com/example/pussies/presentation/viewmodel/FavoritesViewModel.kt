package com.example.pussies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pussies.domain.Pussy
import com.example.pussies.domain.usecases.PussyUseCasesFacade
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val useCases: PussyUseCasesFacade
) : ViewModel() {

    val pussyList: LiveData<List<Pussy>> = useCases.getPussyListFromFavoriteUseCase()

    suspend fun removeFromFavorites(pussyId: String) {
        viewModelScope.launch {
            useCases.deletePussyFromFavoriteUseCase(pussyId)
        }
    }
}