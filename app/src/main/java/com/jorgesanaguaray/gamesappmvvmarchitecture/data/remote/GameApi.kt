package com.jorgesanaguaray.gamesappmvvmarchitecture.data.remote

import com.jorgesanaguaray.gamesappmvvmarchitecture.util.Constants.Companion.GAMES_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Jorge Sanaguaray
 */

interface GameApi {

    @GET(GAMES_ENDPOINT)
    suspend fun getGames(): Response<List<GameModel>>

}