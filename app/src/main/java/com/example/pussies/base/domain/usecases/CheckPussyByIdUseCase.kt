package com.example.pussies.base.domain.usecases

import com.example.pussies.base.domain.PussyRepository
import javax.inject.Inject

class CheckPussyByIdUseCase @Inject constructor(
    private val repository: PussyRepository
) {
    suspend operator fun invoke(pussyId: String): Boolean {
        return repository.checkPussyById(pussyId)
    }
}