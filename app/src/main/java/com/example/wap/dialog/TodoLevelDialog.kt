package com.example.wap.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.wap.R
import com.example.wap.databinding.DialogTodoLevelBinding

class TodoLevelDialog : DialogFragment() {
    
    private val binding by lazy{ DialogTodoLevelBinding.inflate(layoutInflater)}

    private lateinit var dialogViewModel: DialogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomFullDialog)
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialogViewModel = ViewModelProvider(requireActivity())[DialogViewModel::class.java]

        binding.todoCard1.setOnClickListener{
           dialogViewModel.setLevel(R.drawable.yellow_flag)
            dismiss()
        }
        binding.todoCard2.setOnClickListener{
            dialogViewModel.setLevel(R.drawable.green_flag)
            dismiss()
        }
        binding.todoCard3.setOnClickListener{
            dialogViewModel.setLevel(R.drawable.red_flag)
            dismiss()
        }
        return binding.root
    }
}