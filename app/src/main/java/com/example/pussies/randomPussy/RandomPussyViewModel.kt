package com.example.pussies.randomPussy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pussies.base.domain.Pussy
import com.example.pussies.base.domain.usecases.DeletePussyFromFavoriteUseCase
import com.example.pussies.base.domain.usecases.CheckPussyByIdUseCase
import com.example.pussies.base.domain.usecases.InsertPussyToFavoriteUseCase
import com.example.pussies.base.domain.usecases.LoadOnePussyDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
                val pussy = withContext(Dispatchers.IO) {
                    loadOnePussyData()
                }
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
            val currentStatus = pussy.value?.isFavorite
            if (currentStatus != null) {
                withContext(Dispatchers.IO) {
                    if (currentStatus == false) {
                        pussy.value?.let { insertPussyToFavorite(it) }
                    } else {
                        pussy.value?.id?.let { deletePussyFromFavorite(it) }
                    }
                }
                _pussy.value = pussy.value?.copy(isFavorite = !currentStatus)
            }
        }
    }

    fun checkFavorite() {
        viewModelScope.launch {
            val checkFavorite = withContext(Dispatchers.IO) {
                pussy.value?.id?.let { checkPussy(it) }
            }
            Log.d("Pussy", "checkFavorite: $checkFavorite")
            _pussy.value?.isFavorite = checkFavorite?: false
            Log.d("Pussy", "pussy after check ${pussy.value}")
        }
    }
}