package com.example.wap.di

import com.example.wap.model.game.GameRepository
import com.example.wap.model.game.GameRepositoryImpl
import com.example.wap.model.todo.TodoRepository
import com.example.wap.model.todo.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoRepository() : TodoRepository {
        return TodoRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideGameRepository() : GameRepository {
        return GameRepositoryImpl()
    }
}