package com.example.pussies.base.domain.usecases

import androidx.lifecycle.LiveData
import com.example.pussies.base.domain.Pussy
import com.example.pussies.base.domain.PussyRepository
import javax.inject.Inject

class GetPussyListFromFavoriteUseCase @Inject constructor(
    private val repository: PussyRepository
) {
    operator fun invoke(): LiveData<List<Pussy>> {
        return repository.getAllPussiesFromFavorite()
    }
}
