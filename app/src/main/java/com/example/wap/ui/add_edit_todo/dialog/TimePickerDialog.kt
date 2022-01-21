package com.example.wap.ui.add_edit_todo.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.wap.databinding.DialogTimePickerBinding
import com.example.wap.ui.add_edit_todo.SetTodoViewModel

class TimePickerDialog : DialogFragment() {

    private val binding by lazy{ DialogTimePickerBinding.inflate(layoutInflater)}

    private lateinit var listener: TimePickerListener

    private lateinit var setTodoViewModel: SetTodoViewModel

    interface TimePickerListener{
        fun onPositiveButtonClick(timeDeadLine: String)
    }

    fun setOnListener(listener: TimePickerListener){
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setTodoViewModel = ViewModelProvider(requireActivity())[SetTodoViewModel::class.java]

        binding.timePicker.setOnTimeChangedListener { picker, hour, min ->
            setTodoViewModel.setTime(setTime(hour, min))
        }

        binding.timePickerYesButton.setOnClickListener{
            setTodoViewModel.currentTime.value?.let{ value ->
                listener.onPositiveButtonClick(value)
            }
            dismiss()
        }

        binding.timePickerNoButton.setOnClickListener{
            dismiss()
        }

        return binding.root
    }

    private fun setTime(hour: Int, min: Int): String{
        val is24 = if(hour > 12){
            "오후"
        } else{
            "오전"
        }
        val inHour = if(hour >= 10){
            if(hour > 12){
                "${hour-12}"
            }
            else { "$hour" }
        } else{
            "0$hour"
        }
        val inMin = if(min >= 10){
            "$min"
        } else{
            "0$min"
        }
        return " $is24 $inHour : $inMin"
    }
}