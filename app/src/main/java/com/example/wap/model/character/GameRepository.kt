package com.example.wap.model.character

interface GameRepository {

    suspend fun makeCharacter(game: GameData)

    suspend fun updateCharacter(game: GameData)

    suspend fun getCharacterById(id: Int): GameData?
}