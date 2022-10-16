package com.jorgesanaguaray.gamesappmvvmarchitecture.domain

import com.jorgesanaguaray.gamesappmvvmarchitecture.data.local.GameEntity
import com.jorgesanaguaray.gamesappmvvmarchitecture.data.remote.GameModel

/**
 * Created by Jorge Sanaguaray
 */

data class GameItem(

    val id: Int,
    val title: String,
    val thumbnail: String

)

fun GameModel.toDomain() = GameItem(id = id, title = title, thumbnail = thumbnail)
fun GameEntity.toDomain() = GameItem(id = id, title = title, thumbnail = thumbnail)
