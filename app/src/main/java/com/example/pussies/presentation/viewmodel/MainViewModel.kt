package com.example.pussies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pussies.domain.Pussy
import com.example.pussies.domain.PussyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: PussyRepository
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
        withContext(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val pussy = repository.loadOnePussyData()
                _pussy.postValue(pussy)
            } catch (e: Exception) {
                _isError.postValue(true)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    suspend fun toggleFavoriteStatus() {
        withContext(Dispatchers.IO) {
            val currentStatus = pussy.value?.isFavorite
            currentStatus?.let {
                if (currentStatus == false) {
                    pussy.value?.let { repository.insertPussyToFavorite(it) }
                } else {
                    pussy.value?.id?.let { repository.deletePussyFromFavorite(it) }
                }
                _pussy.postValue(pussy.value?.copy(isFavorite = !currentStatus))
            }
        }
    }
}