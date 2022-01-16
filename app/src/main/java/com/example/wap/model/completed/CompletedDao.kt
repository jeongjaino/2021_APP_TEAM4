package com.example.wap.model.completed

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface CompletedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompletedTodo(todo: CompletedTodo)

    @Query("SELECT * FROM CompletedTodo")
    fun getCompletedTodos(): Flow<List<CompletedTodo>>
}