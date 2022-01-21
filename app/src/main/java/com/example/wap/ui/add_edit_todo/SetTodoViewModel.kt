package com.example.wap.ui.add_edit_todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wap.R
import java.text.SimpleDateFormat

class SetTodoViewModel: ViewModel() {

    private val _currentTime = MutableLiveData<String>()
    val currentTime : LiveData<String> = _currentTime

    private val _currentDate = MutableLiveData<String>()
    val currentDate : LiveData<String> = _currentDate

    private val _currentDrawable = MutableLiveData<Int>()
    val currentDrawable: LiveData<Int> = _currentDrawable

    init{
       initValues()
    }

    fun setDate(date: String){
        _currentDate.value = date
    }

    fun setTime(time: String){
        _currentTime.value = time
    }

    fun setDrawable(drawable: Int){
        _currentDrawable.value = drawable
    }

    fun initValues(){
        _currentDate.value = SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis())
        _currentTime.value = ""
        _currentDrawable.value = R.drawable.yellow_flag
    }
    //선택 안했을떄
    //livedata 초기화
}