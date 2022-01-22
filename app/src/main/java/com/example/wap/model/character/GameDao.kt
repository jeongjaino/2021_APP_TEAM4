package com.example.wap.model.character

import androidx.room.*


@Dao
interface GameDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun makeCharacter(game: GameData)

    @Update
    suspend fun updateCharacter(game: GameData)

    @Query("SELECT * FROM GameData WHERE id = :id")
    suspend fun getCharacterById(id: Int): GameData?

}