package com.example.pussies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pussies.domain.Pussy
import com.example.pussies.domain.usecases.PussyUseCasesFacade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailedInfoViewModel @Inject constructor(
    private val useCases: PussyUseCasesFacade
) : ViewModel() {

    private val _pussy = MutableLiveData<Pussy>()
    val pussy: LiveData<Pussy> = _pussy

    private val _statsIsOpen = MutableLiveData(false)
    val statsIsOpen: LiveData<Boolean> = _statsIsOpen

    fun setPussy(pussy: Pussy) {
        _pussy.postValue(pussy)
    }

    /**
     * TODO этот же метод есть в другой вью модели, как правильно поступить?
     *
     */
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

    fun toggleStats() {
        _statsIsOpen.value = _statsIsOpen.value?.not() ?: false
    }

}