package com.example.pussies.detailedInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pussies.base.domain.Pussy
import com.example.pussies.base.domain.usecases.DeletePussyFromFavoriteUseCase
import com.example.pussies.base.domain.usecases.InsertPussyToFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailedInfoViewModel @Inject constructor(
    private val insertPussyToFavorite: InsertPussyToFavoriteUseCase,
    private val deletePussyFromFavorite: DeletePussyFromFavoriteUseCase
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

    fun toggleStats() {
        _statsIsOpen.value = _statsIsOpen.value?.not() ?: false
    }
}