package com.example.pussies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun getBreedsForSearch(): String {
    val breeds = listOf(
        "beng",
        "abys",
        "sfol",
        "nebe",
        "tang",
        "ragd",
        "raga",
        "lape",
        "sava",
        "lihu",
        "bsho",
        "orie",
        "munc"
    )

    return breeds.joinToString(",")
}

private const val TAG = "MainViewModel"

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _pussy = MutableLiveData<Pussy>()
    val pussy: LiveData<Pussy> = _pussy

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    suspend fun loadPussyData() {
        withContext(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val pussies = RetrofitClient.instance.loadPussyData(getBreedsForSearch())
                if (pussies.isNotEmpty()) {
                    val pussy = pussies[0]
                    _pussy.postValue(pussy)
                } else {
                    throw Exception("Empty response")
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                _isError.postValue(true)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}