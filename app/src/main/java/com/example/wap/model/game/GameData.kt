package com.example.wap.model.game

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity
data class GameData (
    @PrimaryKey val id: Int? = null,
    val level: Int = 0,
    val exp: Int = 0
)