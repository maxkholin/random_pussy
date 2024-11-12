package com.example.pussies.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pussies.data.database.AppDatabase
import com.example.pussies.data.mapper.PussyMapper
import com.example.pussies.data.network.ApiFactory
import com.example.pussies.data.repository.PussyRepositoryImpl
import com.example.pussies.domain.Pussy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MyApp"

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = PussyRepositoryImpl(
        apiService = ApiFactory.instance,
        database = AppDatabase.getInstance(application),
        mapper = PussyMapper()
    )

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
                }  else {
                    pussy.value?.id?.let { repository.deletePussyFromFavorite(it) }
                }
                _pussy.postValue(pussy.value?.copy(isFavorite = !currentStatus))
            }
        }
    }
}