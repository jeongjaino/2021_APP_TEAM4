package com.example.wap.dialog.character

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.wap.R
import com.example.wap.databinding.DialogSaveGameBinding

class SaveGameDialog : DialogFragment() {

    private val binding by lazy{DialogSaveGameBinding.inflate(layoutInflater)}

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

        binding.savePositiveButton.setOnClickListener {
            listener.onPositiveClick()
            dismiss()
        }
        binding.saveNegativeButton.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    interface SaveDialogListener{
        fun onPositiveClick()
    }

    private lateinit var listener: SaveDialogListener

    fun setListener(listener: SaveDialogListener){
        this.listener = listener
    }

}