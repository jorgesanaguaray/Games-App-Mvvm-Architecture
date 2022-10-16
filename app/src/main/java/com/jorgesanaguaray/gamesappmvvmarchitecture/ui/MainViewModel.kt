package com.jorgesanaguaray.gamesappmvvmarchitecture.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesanaguaray.gamesappmvvmarchitecture.domain.GameItem
import com.jorgesanaguaray.gamesappmvvmarchitecture.domain.GetGamesFromApiUseCase
import com.jorgesanaguaray.gamesappmvvmarchitecture.domain.GetGamesFromDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGamesFromApiUseCase: GetGamesFromApiUseCase,
    private val getGamesFromDatabaseUseCase: GetGamesFromDatabaseUseCase
    ) : ViewModel() {

    private val _games = MutableLiveData<List<GameItem>>()
    val games: LiveData<List<GameItem>> get() = _games

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> get() = _progressBarVisibility

    private val _recyclerViewVisibility = MutableLiveData<Boolean>()
    val recyclerViewVisibility: LiveData<Boolean> get() = _recyclerViewVisibility

    private val _textViewVisibility = MutableLiveData<Boolean>()
    val textViewVisibility: LiveData<Boolean> get() = _textViewVisibility

    fun getGamesFromApi() {

        showProgressBar()

        viewModelScope.launch {

            try {

                val games = getGamesFromApiUseCase()

                if (games.isNotEmpty()) {
                    _games.value = games
                    showRecyclerView()
                }
                else getGamesFromDatabase()


            } catch (e: Exception) { getGamesFromDatabase() }

        }

    }

    fun getGamesFromDatabase() {

        viewModelScope.launch {

            val games = getGamesFromDatabaseUseCase()

            if (games.isNotEmpty()) {
                _games.value = games
                showRecyclerView()
            } else {
                _message.value = "Error"
                showTextView()
            }

        }

    }

    private fun showProgressBar() {
        _progressBarVisibility.value = true
        _recyclerViewVisibility.value = false
        _textViewVisibility.value = false
    }

    private fun showRecyclerView() {
        _recyclerViewVisibility.value = true
        _textViewVisibility.value = false
        _progressBarVisibility.value = false
    }

    private fun showTextView() {
        _textViewVisibility.value = true
        _recyclerViewVisibility.value = false
        _progressBarVisibility.value = false
    }

}