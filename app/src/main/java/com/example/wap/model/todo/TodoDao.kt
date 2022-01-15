package com.example.wap.model.todo

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoData)

    @Delete
    suspend fun deleteTodo(todo: TodoData)

    @Query("SELECT * FROM TodoData WHERE id = :id")
    suspend fun getTodoById(id: Int): TodoData?

    @Query("SELECT * FROM TodoData")
    fun getTodos(): Flow<List<TodoData>>

    @Update
    suspend fun updateTodo(todo: TodoData)
}