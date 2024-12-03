package com.example.pussies.randomPussy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pussies.base.domain.Pussy
import com.example.pussies.base.domain.usecases.CheckPussyByIdUseCase
import com.example.pussies.base.domain.usecases.DeletePussyFromFavoriteUseCase
import com.example.pussies.base.domain.usecases.InsertPussyToFavoriteUseCase
import com.example.pussies.base.domain.usecases.LoadOnePussyDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomPussyViewModel @Inject constructor(
    private val loadOnePussyData: LoadOnePussyDataUseCase,
    private val insertPussyToFavorite: InsertPussyToFavoriteUseCase,
    private val deletePussyFromFavorite: DeletePussyFromFavoriteUseCase,
    private val checkPussy: CheckPussyByIdUseCase
) : ViewModel() {

    private val _state = MutableLiveData<PussyState>()
    val state: LiveData<PussyState> = _state

    init {
        loadPussyData()
    }

    fun loadPussyData() {
        viewModelScope.launch {
            _state.value = Loading
            try {
                val pussy = loadOnePussyData()
                _state.value = Success(pussy)
            } catch (e: Exception) {
                _state.value = Error("Failed to load data")
            }
        }
    }

    fun toggleFavoriteStatus() {
        viewModelScope.launch {
            val currentState = state.value
            if (currentState is Success) {
                val pussy = currentState.pussy
                val currentStatus = pussy.isFavorite
                if (!currentStatus) {
                    insertPussyToFavorite(pussy)
                } else {
                    deletePussyFromFavorite(pussyId = pussy.id)
                }

                _state.value = Success(pussy.copy(isFavorite = !currentStatus))
            }
        }
    }

    fun checkFavorite() {
        viewModelScope.launch {
            val currentState = _state.value
            if (currentState is Success) {
                val checkFavorite = checkPussy(currentState.pussy.id)
                _state.value = Success(currentState.pussy.copy(isFavorite = checkFavorite))
            }
        }
    }

}