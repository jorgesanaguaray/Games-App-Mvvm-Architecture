package com.jorgesanaguaray.gamesappmvvmarchitecture.repo

import com.jorgesanaguaray.gamesappmvvmarchitecture.data.local.GameDao
import com.jorgesanaguaray.gamesappmvvmarchitecture.data.local.GameEntity
import com.jorgesanaguaray.gamesappmvvmarchitecture.data.remote.GameService
import com.jorgesanaguaray.gamesappmvvmarchitecture.domain.GameItem
import com.jorgesanaguaray.gamesappmvvmarchitecture.domain.toDomain
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class GameRepository @Inject constructor(
    private val gameService: GameService,
    private val gameDao: GameDao
    ) {

    suspend fun getGamesFromApi(): List<GameItem> {

        val games = gameService.getGames()
        return games.map { it.toDomain() }

    }

    suspend fun getGamesFromDatabase(): List<GameItem> {

        val games = gameDao.getGames()
        return games.map { it.toDomain() }

    }

    suspend fun insertGames(games: List<GameEntity>) {
        gameDao.insertGames(games)
    }

    suspend fun deleteGames() {
        gameDao.deleteGames()
    }

}