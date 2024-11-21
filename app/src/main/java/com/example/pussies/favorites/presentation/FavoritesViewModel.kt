package com.example.pussies.favorites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pussies.base.domain.Pussy
import com.example.pussies.base.domain.usecases.DeletePussyFromFavoriteUseCase
import com.example.pussies.base.domain.usecases.GetPussyListFromFavoriteUseCase
import com.example.pussies.base.presentation.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    getPussyListFromFavorite: GetPussyListFromFavoriteUseCase,
    private val deletePussyFromFavorite: DeletePussyFromFavoriteUseCase
) : BaseViewModel() {

    val pussyList: LiveData<List<Pussy>> = getPussyListFromFavorite()

    fun removeFromFavorites(pussyId: String) {
        viewModelScope.launch {
            deletePussyFromFavorite(pussyId)
        }
    }
}