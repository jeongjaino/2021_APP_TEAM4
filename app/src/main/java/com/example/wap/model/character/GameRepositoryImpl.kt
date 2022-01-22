package com.example.wap.model.character

class GameRepositoryImpl(
    private val dao: GameDao
): GameRepository {

    override suspend fun makeCharacter(game: GameData) {
        dao.makeCharacter(game)
    }

    override suspend fun updateCharacter(game: GameData) {
        dao.updateCharacter(game)
    }

    override suspend fun getCharacterById(id: Int): GameData?{
        return dao.getCharacterById(id)
    }
}