package com.example.pussies.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pussies.data.mapper.PussyMapper
import com.example.pussies.data.network.ApiFactory
import com.example.pussies.domain.Pussy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "MyApp"

class MainViewModel(
//    private val mapper: PussyMapper
) : ViewModel() {
    private val mapper = PussyMapper()

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
                val pussyList = ApiFactory.instance.loadPussyData()
                if (pussyList.isNotEmpty()) {
                    val pussyDto = pussyList[0]
                    val pussy = mapper.mapDtoToDomain(pussyDto, true)

                    Log.d(TAG, pussy.catFriendly)
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

    fun addRemoveFromFavorite() {

    }
}