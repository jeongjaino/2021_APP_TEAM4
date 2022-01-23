package com.example.wap.ui.mini_game

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.animation.addListener
import androidx.lifecycle.ViewModelProvider
import com.example.wap.databinding.FragmentTouchGameBinding
import com.example.wap.ui.character.CharacterViewModel

class TouchGameFragment : Fragment() {

    private val binding by lazy{ FragmentTouchGameBinding.inflate(layoutInflater)}

    private lateinit var characterViewModel: CharacterViewModel

    private var gold = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        characterViewModel = ViewModelProvider(requireActivity())[CharacterViewModel::class.java]

        binding.touchGameImage.setOnClickListener{
            gold += 1
            binding.touchGameGold.text = gold.toString() + "G"
            movePet(binding.touchGameImage)
        }
        return binding.root
    }
    private fun movePet(pet: ImageView) {

        val width = (binding.touchLayout.width - binding.touchGameImage.width)/2
        val height = (binding.touchLayout.height - binding.touchGameImage.height)/2

        val nextPosX = (-width..width).random().toFloat()
        val nextPosY = (-height..height).random().toFloat()
        val nextAngle = (180..360).random().toFloat()

        ObjectAnimator.ofFloat(pet, "translationX", nextPosX).apply {
            duration = 1000
            start()
        }
        ObjectAnimator.ofFloat(pet, "translationY", nextPosY).apply {
            duration = 1000
            start()
        }
        ObjectAnimator.ofFloat(pet, "rotation", 0f, nextAngle).apply {
            duration = 1000
            start()
        }
    }


    override fun onPause() {
        super.onPause()
        //dialog 하나 띄우면 좋을듯 게임을 종료하시겠습니까    ?
        // 클릭하면 그때 update하고
        characterViewModel.updateGold(gold)
    }
}