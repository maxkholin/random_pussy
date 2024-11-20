package com.example.pussies.base.domain.usecases

import com.example.pussies.base.domain.Pussy
import com.example.pussies.base.domain.PussyRepository
import javax.inject.Inject

class LoadOnePussyDataUseCase @Inject constructor(
    private val repository: PussyRepository
) {

    suspend operator fun invoke(): Pussy {
        return repository.loadOnePussyData()
    }
}