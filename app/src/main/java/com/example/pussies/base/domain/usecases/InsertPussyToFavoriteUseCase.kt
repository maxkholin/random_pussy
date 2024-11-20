package com.example.pussies.base.domain.usecases

import com.example.pussies.base.domain.Pussy
import com.example.pussies.base.domain.PussyRepository
import javax.inject.Inject

class InsertPussyToFavoriteUseCase @Inject constructor(
    private val repository: PussyRepository
) {
    suspend operator fun invoke(pussy: Pussy) {
        repository.insertPussyToFavorite(pussy)
    }
}
