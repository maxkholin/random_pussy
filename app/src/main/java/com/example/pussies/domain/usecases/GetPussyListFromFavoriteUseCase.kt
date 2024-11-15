package com.example.pussies.domain.usecases

import androidx.lifecycle.LiveData
import com.example.pussies.domain.Pussy
import com.example.pussies.domain.PussyRepository
import javax.inject.Inject

class GetPussyListFromFavoriteUseCase @Inject constructor(
    private val repository: PussyRepository
) {
    operator fun invoke(): LiveData<List<Pussy>> {
        return repository.getAllPussiesFromFavorite()
    }
}
