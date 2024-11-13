package com.example.pussies.domain.usecases

import com.example.pussies.domain.Pussy
import com.example.pussies.domain.PussyRepository
import javax.inject.Inject

class LoadOnePussyDataUseCase @Inject constructor(
    private val repository: PussyRepository
) {

    suspend operator fun invoke(): Pussy {
       return repository.loadOnePussyData()
    }
}