package com.example.wap.model.completed

import kotlinx.coroutines.flow.Flow

interface CompletedRepository {

    suspend fun insertCompletedTodo(todo: CompletedTodo)

    fun getCompletedTodos(): Flow<List<CompletedTodo>>
}