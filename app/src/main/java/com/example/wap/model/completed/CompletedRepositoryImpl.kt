package com.example.wap.model.completed

import kotlinx.coroutines.flow.Flow


class CompletedRepositoryImpl(
    private val dao: CompletedDao
): CompletedRepository {

    override suspend fun insertCompletedTodo(todo: CompletedTodo) {
        return dao.insertCompletedTodo(todo)
    }

    override fun getCompletedTodos(): Flow<List<CompletedTodo>> {
        return dao.getCompletedTodos()
    }
}