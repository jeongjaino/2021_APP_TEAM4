package com.example.wap.model.game

import androidx.room.*
import com.example.wap.model.todo.TodoData
import kotlinx.coroutines.flow.Flow


@Dao
interface GameDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun makeCharacter(game: GameData)

    @Update
    suspend fun updateCharacter(game: GameData)

    @Query("SELECT * FROM GameData WHERE id = :id")
    suspend fun getCharacterById(id: Int): GameData?

}