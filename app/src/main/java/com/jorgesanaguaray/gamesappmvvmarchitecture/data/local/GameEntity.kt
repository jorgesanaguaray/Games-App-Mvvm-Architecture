package com.jorgesanaguaray.gamesappmvvmarchitecture.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

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
