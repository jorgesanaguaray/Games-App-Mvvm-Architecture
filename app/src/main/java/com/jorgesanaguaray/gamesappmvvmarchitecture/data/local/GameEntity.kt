package com.jorgesanaguaray.gamesappmvvmarchitecture.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jorgesanaguaray.gamesappmvvmarchitecture.domain.GameItem

/**
 * Created by Jorge Sanaguaray
 */

@Entity(tableName = "game_table")
data class GameEntity(

    @PrimaryKey
    val id: Int,
    val title: String,
    val thumbnail: String

)

fun GameItem.toDatabase() = GameEntity(id = id, title = title, thumbnail = thumbnail)
