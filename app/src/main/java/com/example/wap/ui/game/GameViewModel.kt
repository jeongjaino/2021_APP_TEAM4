package com.example.wap.ui.game

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

    private val _level = MutableLiveData<GameData>()

    val level: LiveData<GameData> get() = _level

    init{
        loadLevel()
    }

    fun loadLevel(){
        viewModelScope.launch {
            _level.value =  gameRepository.loadLevel()
        }
    }
    fun updateLevel() {
        viewModelScope.launch {
            gameRepository.updateLevel(levelUp())
        }
    }

    private fun levelUp() : GameData {

        val information = _level.value!!

        var nlevel = information.level
        var nexp = information.exp

        when {
            nlevel in 1..9 -> {
                nexp += 50
            }
            nlevel in 10..30 -> {
                nexp += 30
            }
            nlevel > 30 -> {
                nexp += 20
            }
        }
        if (nexp >= 100) {
            nlevel += 1
            nexp -= 100
        }
        val nGameData = GameData(nlevel,nexp)

        viewModelScope.launch {
            _level.value = nGameData
        }
        return nGameData
    }
}