package com.jorgesanaguaray.gamesappmvvmarchitecture.domain

import com.jorgesanaguaray.gamesappmvvmarchitecture.repo.GameRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by Jorge Sanaguaray
 */

class GetGamesFromApiUseCaseTest {

    lateinit var getGamesFromApiUseCase: GetGamesFromApiUseCase

    @RelaxedMockK
    private lateinit var gameRepository: GameRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getGamesFromApiUseCase = GetGamesFromApiUseCase(gameRepository)
    }

    @Test
    fun `when GamRepository returns a list of games, return the list of games`() = runBlocking {

        // Given
        val games = listOf(
            GameItem(id = 0, title = "a", thumbnail = "a"),
            GameItem(id = 1, title = "b", thumbnail = "b"),
            GameItem(id = 2, title = "c", thumbnail = "c")
        )
        coEvery { gameRepository.getGamesFromApi() } returns games

        // When
        val response = getGamesFromApiUseCase()

        // Then
        coVerify(exactly = 1) { gameRepository.deleteGames() }
        coVerify(exactly = 1) { gameRepository.insertGames(any()) }
        assert(games == response)

    }

    @Test
    fun `when GameRepository returns an empty list, return the empty list`() = runBlocking {

        // Given
        coEvery { gameRepository.getGamesFromApi() } returns emptyList()

        // When
        val games = getGamesFromApiUseCase()

        // Then
        coVerify(exactly = 0) { gameRepository.deleteGames() }
        coVerify(exactly = 0) { gameRepository.insertGames(any()) }
        assert(games.isEmpty())

    }

}