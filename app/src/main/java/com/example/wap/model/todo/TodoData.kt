package com.example.wap.model.todo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoData(
    val toDo: String?,
    val deadline: String?,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null
)
