package com.jorgesanaguaray.gamesappmvvmarchitecture.domain

import com.jorgesanaguaray.gamesappmvvmarchitecture.data.local.toDatabase
import com.jorgesanaguaray.gamesappmvvmarchitecture.repo.GameRepository
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class GetGamesFromApiUseCase @Inject constructor(private val gameRepository: GameRepository) {

    suspend operator fun invoke(): List<GameItem> {

        val games = gameRepository.getGamesFromApi()

        return if (games.isNotEmpty()) {
            gameRepository.deleteGames()
            gameRepository.insertGames(games.map { it.toDatabase() })
            games
        } else {
            emptyList()
        }

    }

}