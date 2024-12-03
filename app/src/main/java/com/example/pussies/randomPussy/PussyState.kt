package com.example.pussies.randomPussy

import com.example.pussies.base.domain.Pussy

sealed class PussyState

data object Loading: PussyState()
data class Error(val error: String): PussyState()
data class Success(val pussy: Pussy): PussyState()