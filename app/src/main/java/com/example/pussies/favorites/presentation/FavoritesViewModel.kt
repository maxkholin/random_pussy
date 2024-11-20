package com.example.pussies.favorites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pussies.domain.Pussy
import com.example.pussies.domain.usecases.DeletePussyFromFavoriteUseCase
import com.example.pussies.domain.usecases.GetPussyListFromFavoriteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    getPussyListFromFavorite: GetPussyListFromFavoriteUseCase,
    private val deletePussyFromFavorite: DeletePussyFromFavoriteUseCase
) : ViewModel() {

    val pussyList: LiveData<List<Pussy>> = getPussyListFromFavorite()

    fun removeFromFavorites(pussyId: String) {
        viewModelScope.launch {
            deletePussyFromFavorite(pussyId)
        }
    }
}