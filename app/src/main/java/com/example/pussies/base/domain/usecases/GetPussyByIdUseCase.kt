package com.example.pussies.base.domain.usecases

import com.example.pussies.base.data.database.PussyDbModel
import com.example.pussies.base.domain.PussyRepository
import javax.inject.Inject

class GetPussyByIdUseCase @Inject constructor(
    private val repository: PussyRepository
) {
    operator fun invoke(pussyId: String): PussyDbModel? {
        return repository.getPussyById(pussyId)
    }
}