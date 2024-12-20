package com.example.pussies.domain.usecases

import com.example.pussies.domain.PussyRepository
import javax.inject.Inject

class DeletePussyFromFavoriteUseCase @Inject constructor(
    private val repository: PussyRepository
) {
    suspend operator fun invoke(pussyId: String) {
        repository.deletePussyFromFavorite(pussyId)
    }
}
