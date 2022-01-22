package com.example.wap.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wap.model.game.GameData
import com.example.wap.model.game.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository
): ViewModel() {

    private val _information = MutableLiveData<GameData>()

    val information: LiveData<GameData> get() = _information

    init{
        viewModelScope.launch {
            if(gameRepository.getCharacterById(1) == null){
                gameRepository.makeCharacter(GameData(1,0,0,1))
                _information.value = GameData(1,0,0,1)
            }
            gameRepository.getCharacterById(1)?.let{
                _information.value = it
                Log.d("tag",it.level.toString() + it.id.toString())

            }
        }
    }

    fun updateLevel() {
        viewModelScope.launch {
            gameRepository.updateCharacter(levelUp())
        }
    }

    private fun levelUp() : GameData {

        val information = _information.value!!

        var nlevel = information.level
        var nexp = information.exp

        when {
            nlevel in 1..9 -> {
                nexp += 30
            }
            nlevel in 10..30 -> {
                nexp += 20
            }
            nlevel in 30..100 -> {
                nexp += 10
            }
            nlevel > 100 ->{
                nexp += 5
            }
        }
        if (nexp >= 100) {
            nlevel += 1
            nexp -= 100
        }

        val nGameData = GameData(nlevel, nexp, information.gold, 1)

        viewModelScope.launch {
            _information.value = nGameData
        }
        return nGameData
    }
}