package com.example.wap.model.todo

import androidx.room.Entity

@Entity
data class TodoData(
    private val id: Int? = null,
    val toDo: String = "",
    val deadline: String = "")
