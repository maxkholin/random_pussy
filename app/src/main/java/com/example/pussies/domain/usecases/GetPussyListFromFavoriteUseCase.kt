package com.example.pussies.domain.usecases

import com.example.pussies.domain.Pussy
import com.example.pussies.domain.PussyRepository
import javax.inject.Inject

class GetPussyListFromFavoriteUseCase @Inject constructor(
    private val repository: PussyRepository
) {
    suspend operator fun invoke(): List<Pussy> {
        return repository.getAllPussiesFromFavorite()
    }
}
