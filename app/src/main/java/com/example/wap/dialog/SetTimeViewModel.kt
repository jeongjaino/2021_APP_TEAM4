package com.example.wap.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat

class SetTimeViewModel: ViewModel() {

    private val _currentTime = MutableLiveData<String>()
    private val _currentDate = MutableLiveData<String>()

    init{
        _currentDate.value = SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis())
        _currentTime.value = ""
    }

    fun setDate(date: String){
        _currentDate.value = date
    }

    fun setTime(time: String){
        _currentTime.value = time
    }

    fun getDateTime(): String{
        return _currentDate.value + _currentTime.value
    }

}