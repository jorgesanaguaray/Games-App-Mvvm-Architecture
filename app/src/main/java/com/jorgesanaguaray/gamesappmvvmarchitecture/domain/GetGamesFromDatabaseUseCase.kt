package com.jorgesanaguaray.gamesappmvvmarchitecture.domain

import com.jorgesanaguaray.gamesappmvvmarchitecture.repo.GameRepository
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class GetGamesFromDatabaseUseCase @Inject constructor(private val gameRepository: GameRepository) {

    suspend operator fun invoke(): List<GameItem> {

        val games = gameRepository.getGamesFromDatabase()

        return games.ifEmpty {
            emptyList()
        }

    }

}