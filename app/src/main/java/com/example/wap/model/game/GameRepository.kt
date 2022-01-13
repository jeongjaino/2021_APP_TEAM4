package com.example.wap.model.game
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    suspend fun makeCharacter(game: GameData)

    suspend fun updateCharacter(game: GameData)

    suspend fun getCharacterById(id: Int): GameData?

    fun getCharacters(): Flow<List<GameData>>
}