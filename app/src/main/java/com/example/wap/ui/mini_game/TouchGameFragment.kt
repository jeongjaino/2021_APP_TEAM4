package com.example.wap.ui.mini_game

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.core.animation.addListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.wap.databinding.FragmentTouchGameBinding
import com.example.wap.dialog.character.SaveGameDialog
import com.example.wap.ui.character.CharacterViewModel

class TouchGameFragment : Fragment() {

    private val binding by lazy{ FragmentTouchGameBinding.inflate(layoutInflater)}

    private lateinit var characterViewModel: CharacterViewModel

    private var gold = 0

    private lateinit var callback: OnBackPressedCallback

    //뒤로 가기 눌렀을때 callback 받아서 처리
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val dialog = SaveGameDialog()
                dialog.setListener(object: SaveGameDialog.SaveDialogListener{
                    override fun onPositiveClick() {
                        characterViewModel.updateGold(gold)
                        val direction: NavDirections = TouchGameFragmentDirections.actionTouchGameFragmentToGameFragment()
                        view!!.findNavController().navigate(direction)
                    }
                })
                dialog.show(activity!!.supportFragmentManager, "saveDialog")
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

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

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}