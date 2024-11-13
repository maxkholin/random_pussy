package com.example.pussies.domain.usecases

import com.example.pussies.domain.Pussy
import com.example.pussies.domain.PussyRepository
import javax.inject.Inject

class InsertPussyToFavoriteUseCase @Inject constructor(
    private val repository: PussyRepository
) {
    suspend operator fun invoke(pussy: Pussy) {
        repository.insertPussyToFavorite(pussy)
    }
}
