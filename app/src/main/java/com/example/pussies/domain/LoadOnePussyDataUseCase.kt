package com.example.pussies.domain

import javax.inject.Inject

class LoadOnePussyDataUseCase @Inject constructor(
    private val repository: PussyRepository
) {

    operator fun invoke() {
        repository.loadOnePussyData()
    }
}