package com.example.wap.ui.mini_game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        }
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        characterViewModel.updateGold(gold)
    }
}