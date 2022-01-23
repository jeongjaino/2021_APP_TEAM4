package com.example.wap.dialog.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.wap.R
import com.example.wap.databinding.DialogSelectGameBinding

class SelectGameDialog : DialogFragment() {

    private val binding by lazy{ DialogSelectGameBinding.inflate(layoutInflater)}

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding.miniGame1Button.setOnClickListener{
            listener.miniGame1Click()
            dismiss()
        }
        binding.miniGame2Button.setOnClickListener{
            listener.miniGame2Click()
            dismiss()
        }
        return binding.root
    }

    interface SelectListener{
        fun miniGame1Click()
        fun miniGame2Click()
    }

    private lateinit var listener: SelectListener

    fun setListener(listener: SelectListener){
        this.listener = listener
    }
}