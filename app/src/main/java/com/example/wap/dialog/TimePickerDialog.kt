package com.example.wap.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.wap.databinding.DialogTimePickerBinding

class TimePickerDialog : DialogFragment() {

    private val binding by lazy{ DialogTimePickerBinding.inflate(layoutInflater)}

    private lateinit var dialogViewModel: DialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialogViewModel = ViewModelProvider(requireActivity())[DialogViewModel::class.java]

        binding.timePicker.setOnTimeChangedListener { picker, hour, min ->
            dialogViewModel.setTime(setTime(hour, min))
        }

        binding.timePickerYesButton.setOnClickListener{
            dismiss()
        }

        binding.timePickerNoButton.setOnClickListener{
            //취소하면 이전의 설정했던 값 초기화
            dialogViewModel.setTime("")
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