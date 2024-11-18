package com.example.pussies.domain.usecases

import javax.inject.Inject

class PussyUseCasesFacade @Inject constructor(
    val deletePussyFromFavoriteUseCase: DeletePussyFromFavoriteUseCase,
    val insertPussyToFavoriteUseCase: InsertPussyToFavoriteUseCase,
    val loadOnePussyDataUseCase: LoadOnePussyDataUseCase,
    val getPussyListFromFavoriteUseCase: GetPussyListFromFavoriteUseCase,
    val checkFavoritePussyUseCase: CheckFavoritePussyUseCase
)