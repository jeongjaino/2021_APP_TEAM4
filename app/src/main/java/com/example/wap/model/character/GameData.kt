package com.example.wap.model.character

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity
data class GameData (
    val level: Int = 0,
    val exp: Int = 0,
    val gold: Int = 0,
    @PrimaryKey val id: Int? = null
)