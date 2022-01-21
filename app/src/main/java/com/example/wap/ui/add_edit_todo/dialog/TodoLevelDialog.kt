package com.example.wap.ui.add_edit_todo.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.wap.R
import com.example.wap.databinding.DialogTodoLevelBinding

class TodoLevelDialog : DialogFragment() {
    
    private val binding by lazy{ DialogTodoLevelBinding.inflate(layoutInflater)}

    private lateinit var listener: TodoLevelDialogListener

    interface TodoLevelDialogListener{
        fun onLevelClick(drawable: Int)
    }

    fun setOnListener(listener: TodoLevelDialogListener){
        this.listener = listener
    }

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

        binding.todoCard1.setOnClickListener{
            listener.onLevelClick(R.drawable.yellow_flag)
            dismiss()
        }
        binding.todoCard2.setOnClickListener{
            listener.onLevelClick(R.drawable.green_flag)
            dismiss()
        }
        binding.todoCard3.setOnClickListener{
            listener.onLevelClick(R.drawable.red_flag)
            dismiss()
        }
        return binding.root
    }
    
}