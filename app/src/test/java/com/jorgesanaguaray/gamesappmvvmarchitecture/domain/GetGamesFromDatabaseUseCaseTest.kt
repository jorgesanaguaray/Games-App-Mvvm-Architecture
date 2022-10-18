package com.jorgesanaguaray.gamesappmvvmarchitecture.domain

import com.jorgesanaguaray.gamesappmvvmarchitecture.repo.GameRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by Jorge Sanaguaray
 */

class GetGamesFromDatabaseUseCaseTest {

    lateinit var getGamesFromDatabaseUseCase: GetGamesFromDatabaseUseCase

    @RelaxedMockK
    private lateinit var gameRepository: GameRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getGamesFromDatabaseUseCase = GetGamesFromDatabaseUseCase(gameRepository)
    }

    @Test
    fun `when GamRepository returns a list of games, return the list of games`() = runBlocking {

        // Given
        val games = listOf(
            GameItem(id = 0, title = "a", thumbnail = "a"),
            GameItem(id = 1, title = "b", thumbnail = "b"),
            GameItem(id = 2, title = "c", thumbnail = "c")
        )
        coEvery { gameRepository.getGamesFromDatabase() } returns games

        // When
        val response = getGamesFromDatabaseUseCase()

        // Then
        assert(games == response)

    }

    @Test
    fun `when GameRepository returns an empty list, return the empty list`() = runBlocking {

        // Given
        coEvery { gameRepository.getGamesFromApi() } returns emptyList()

        // When
        val games = getGamesFromDatabaseUseCase()

        // Then
        assert(games.isEmpty())

    }

}