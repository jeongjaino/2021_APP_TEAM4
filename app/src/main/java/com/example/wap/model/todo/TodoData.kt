package com.example.wap.model.todo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoData(
    val todo: String?,
    val date: String?,
    val time: String?,
    val level: Int?,
    @PrimaryKey val id: Int? = null
)
