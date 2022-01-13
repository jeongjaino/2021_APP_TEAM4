package com.example.wap.model

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wap.model.game.GameDao
import com.example.wap.model.game.GameData
import com.example.wap.model.todo.TodoDao
import com.example.wap.model.todo.TodoData

@Database(
    entities = [GameData::class, TodoData::class],
    version = 1
)
abstract class App4Database: RoomDatabase(){

    abstract val todoDao: TodoDao

    abstract val gameDao: GameDao
}