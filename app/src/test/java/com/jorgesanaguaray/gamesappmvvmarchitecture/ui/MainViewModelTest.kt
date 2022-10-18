package com.jorgesanaguaray.gamesappmvvmarchitecture.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jorgesanaguaray.gamesappmvvmarchitecture.domain.GameItem
import com.jorgesanaguaray.gamesappmvvmarchitecture.domain.GetGamesFromApiUseCase
import com.jorgesanaguaray.gamesappmvvmarchitecture.domain.GetGamesFromDatabaseUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Jorge Sanaguaray
 */

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @RelaxedMockK
    private lateinit var getGamesFromApiUseCase: GetGamesFromApiUseCase

    @RelaxedMockK
    private lateinit var getGamesFromDatabaseUseCase: GetGamesFromDatabaseUseCase

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(getGamesFromApiUseCase, getGamesFromDatabaseUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getGamesFromApiUseCase() returns games, check games LiveData value was set`()= runTest {

        // Given
        val games = listOf(
            GameItem(id = 0, title = "a", thumbnail = "a"),
            GameItem(id = 1, title = "b", thumbnail = "b"),
            GameItem(id = 2, title = "c", thumbnail = "c")
        )
        coEvery { getGamesFromApiUseCase() } returns games

        // When
        mainViewModel.getGamesFromApi()

        // Then
        assert(mainViewModel.games.value == games)

    }

    @Test
    fun `when getGamesFromDatabaseUseCase() returns games, check games LiveData value was set`()= runTest {

        // Given
        val games = listOf(
            GameItem(id = 0, title = "a", thumbnail = "a"),
            GameItem(id = 1, title = "b", thumbnail = "b"),
            GameItem(id = 2, title = "c", thumbnail = "c")
        )
        coEvery { getGamesFromDatabaseUseCase() } returns games

        // When
        mainViewModel.getGamesFromDatabase()

        // Then
        assert(mainViewModel.games.value == games)

    }

    @Test
    fun `when both use cases return empty lists, check message LiveData was set`()= runTest {

        // Given
        coEvery { getGamesFromApiUseCase() } returns emptyList()
        coEvery { getGamesFromDatabaseUseCase() } returns emptyList()

        // When
        mainViewModel.getGamesFromApi()
        mainViewModel.getGamesFromDatabase()

        // Then
        assert(mainViewModel.message.value != null)

    }

}