package com.example.pussies.domain.usecases

import com.example.pussies.domain.Pussy
import com.example.pussies.domain.PussyRepository
import javax.inject.Inject

class GetAllPussiesFromFavoriteUseCase @Inject constructor(
    private val repository: PussyRepository
) {
    operator fun invoke(): List<Pussy> {
        return repository.getAllPussiesFromFavorite()
    }
}
