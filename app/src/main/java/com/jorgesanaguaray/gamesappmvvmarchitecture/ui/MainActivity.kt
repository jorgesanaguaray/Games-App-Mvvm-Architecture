package com.jorgesanaguaray.gamesappmvvmarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.jorgesanaguaray.gamesappmvvmarchitecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Jorge Sanaguaray
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var gameAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get()
        gameAdapter = GameAdapter()

        mainViewModel.games.observe(this) {
            gameAdapter.setGames(it.shuffled())
            binding.mRecyclerView.adapter = gameAdapter
        }

        mainViewModel.message.observe(this) {
            binding.mTextViewMessage.text = it
        }

        mainViewModel.recyclerViewVisibility.observe(this) {
            binding.mRecyclerView.visibility = if (it) View.VISIBLE else View.GONE
        }

        mainViewModel.progressBarVisibility.observe(this) {
            binding.mProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        mainViewModel.textViewVisibility.observe(this) {
            binding.mTextViewMessage.visibility = if (it) View.VISIBLE else View.GONE
        }

        mainViewModel.getGamesFromApi()

    }

}