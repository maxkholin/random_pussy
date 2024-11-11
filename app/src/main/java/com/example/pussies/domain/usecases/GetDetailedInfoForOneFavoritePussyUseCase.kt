package com.example.pussies.domain.usecases

import com.example.pussies.domain.Pussy
import com.example.pussies.domain.PussyRepository
import javax.inject.Inject

class GetDetailedInfoForOneFavoritePussyUseCase @Inject constructor(
    private val repository: PussyRepository
) {
    operator fun invoke(pussyId: String): Pussy {
        return repository.getDetailedInfoForOneFavoritePussy(pussyId)
    }
}
