package com.example.pussies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pussies.domain.Pussy
import com.example.pussies.domain.usecases.PussyUseCasesFacade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RandomPussyViewModel @Inject constructor(
    private val useCases: PussyUseCasesFacade
) : ViewModel() {

    private val _pussy = MutableLiveData<Pussy>()
    val pussy: LiveData<Pussy> = _pussy

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    init {
        viewModelScope.launch {
            loadPussyData()
        }
    }

    suspend fun loadPussyData() {
        /* TODO в чем разница если я сделаю  withContext(Dispatchers.IO) или viewModelScope.launch
        *   в FavoritesViewModel в методе removeFromFavorites()*/
        withContext(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val pussy = useCases.loadOnePussyDataUseCase()
                _pussy.postValue(pussy)
            } catch (e: Exception) {
                _isError.postValue(true)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun resetError() {
        _isError.postValue(false)
    }

    suspend fun toggleFavoriteStatus() {
        withContext(Dispatchers.IO) {
            val currentStatus = pussy.value?.isFavorite
            currentStatus?.let {
                if (currentStatus == false) {
                    pussy.value?.let { useCases.insertPussyToFavoriteUseCase(it) }
                } else {
                    pussy.value?.id?.let { useCases.deletePussyFromFavoriteUseCase(it) }
                }
                _pussy.postValue(pussy.value?.copy(isFavorite = !currentStatus))
            }
        }
    }

}