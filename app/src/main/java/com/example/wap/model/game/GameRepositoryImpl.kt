package com.example.wap.model.game

import kotlinx.coroutines.flow.Flow

class GameRepositoryImpl(
    private val dao: GameDao
): GameRepository {

    override suspend fun makeCharacter(game: GameData) {
        dao.makeCharacter(game)
    }

    override suspend fun updateCharacter(game: GameData) {
        dao.updateCharacter(game)
    }

    override fun getCharacters(): Flow<List<GameData>> {
       return dao.getCharacters()
    }

    override suspend fun getCharacterById(id: Int): GameData? {
        return dao.getCharacterById(id)
    }
}