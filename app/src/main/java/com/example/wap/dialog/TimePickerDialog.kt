package com.example.wap.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.wap.databinding.DialogTimePickerBinding

class TimePickerDialog : DialogFragment() {

    private val binding by lazy{ DialogTimePickerBinding.inflate(layoutInflater)}

    private lateinit var listener: TimePickerListener

    private var timeDeadLine: String = ""

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

        binding.timePicker.setOnTimeChangedListener { picker, hour, min ->
            timeDeadLine = setTime(picker.is24HourView, hour, min)
        }
        binding.timePickerYesButton.setOnClickListener{
            listener.onPositiveButtonClick(timeDeadLine)
            dismiss()
        }
        binding.timePickerNoButton.setOnClickListener{
            dismiss()
        }
        return binding.root
    }

    private fun setTime(is24Hour: Boolean, hour: Int, min: Int): String{
        val is24 = if(is24Hour){
            "오후"
        } else{
            "오전"
        }
        val inHour = if(hour >= 10){
            "$hour"
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