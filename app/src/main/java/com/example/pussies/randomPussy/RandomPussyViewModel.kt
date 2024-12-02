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

    private val _pussy = MutableLiveData<Pussy>()
    val pussy: LiveData<Pussy> = _pussy

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    init {
        loadPussyData()
    }

    fun loadPussyData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val pussy = loadOnePussyData()
                _pussy.value = pussy
            } catch (e: Exception) {
                _isError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun resetError() {
        _isError.value = false
    }

    fun toggleFavoriteStatus() {
        viewModelScope.launch {
            pussy.value?.let {
                val currentStatus = it.isFavorite
                if (!currentStatus)
                    insertPussyToFavorite(it)
                else
                    deletePussyFromFavorite(it.id)

                _pussy.value = it.copy(isFavorite = !currentStatus)
            }
        }
    }

    fun checkFavorite() {
        viewModelScope.launch {
            pussy.value?.let {
                val checkFavorite = checkPussy(it.id)
                _pussy.value?.isFavorite = checkFavorite
            }
        }
    }
}