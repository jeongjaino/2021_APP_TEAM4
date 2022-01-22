package com.example.wap.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wap.model.completed.CompletedDao
import com.example.wap.model.completed.CompletedTodo
import com.example.wap.model.character.GameDao
import com.example.wap.model.character.GameData
import com.example.wap.model.todo.TodoDao
import com.example.wap.model.todo.TodoData

@Database(
    entities = [GameData::class, TodoData::class, CompletedTodo::class],
    version = 1
)
abstract class App4Database: RoomDatabase(){

    abstract val todoDao: TodoDao

    abstract val gameDao: GameDao

    abstract val completedDao: CompletedDao
}