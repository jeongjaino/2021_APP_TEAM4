package com.example.wap.model.todo

import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTodo(todo: TodoData)

    suspend fun deleteTodo(todo: TodoData)

    suspend fun getTodoById(id: Int): TodoData?

    fun getTodos(): Flow<List<TodoData>>

    suspend fun updateTodo(todo: TodoData)

}