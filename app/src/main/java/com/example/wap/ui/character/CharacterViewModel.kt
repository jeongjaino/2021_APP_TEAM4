package com.example.wap.ui.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wap.R
import com.example.wap.model.character.GameData
import com.example.wap.model.character.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
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

    fun updateLevel(todoLevel: Int) {
        viewModelScope.launch {
            gameRepository.updateCharacter(levelUp(todoLevel))
        }
    }

    fun updateGold(gold: Int){
        viewModelScope.launch {
            val totalGold = _information.value!!.gold + gold
            _information.value = _information.value!!.copy(gold = totalGold)
            gameRepository.updateCharacter(_information.value!!)
        }
    }

    private fun levelUp(level: Int) : GameData {

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
        when(level){
            R.drawable.yellow_flag ->{
                nexp += 5
            }
            R.drawable.green_flag ->{
                nexp += 10
            }
            R.drawable.red_flag ->{
                nexp += 20
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