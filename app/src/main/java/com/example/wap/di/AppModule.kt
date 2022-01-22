package com.example.wap.di

import android.app.Application
import androidx.room.Room
import com.example.wap.model.App4Database
import com.example.wap.model.completed.CompletedRepository
import com.example.wap.model.completed.CompletedRepositoryImpl
import com.example.wap.model.character.GameRepository
import com.example.wap.model.character.GameRepositoryImpl
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
    fun provideDatabase(app: Application): App4Database{
        return Room.databaseBuilder(
            app,
            App4Database::class.java,
            "app4_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: App4Database) : TodoRepository {
        return TodoRepositoryImpl(db.todoDao)
    }

    @Provides
    @Singleton
    fun provideGameRepository(db: App4Database) : GameRepository {
        return GameRepositoryImpl(db.gameDao)
    }


    @Provides
    @Singleton
    fun provideCompletedRepository(db: App4Database): CompletedRepository{
        return CompletedRepositoryImpl(db.completedDao)
    }
}