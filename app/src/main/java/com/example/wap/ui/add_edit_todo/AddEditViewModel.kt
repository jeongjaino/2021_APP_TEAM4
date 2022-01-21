package com.example.wap.ui.add_edit_todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wap.model.todo.TodoData
import com.example.wap.model.todo.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val todoRepository: TodoRepository
): ViewModel() {

    private val _todoList = MutableLiveData<TodoData>()

    val todoList : LiveData<TodoData> = _todoList

    fun getTodoById(position: Int){
        viewModelScope.launch {
            todoRepository.getTodoById(position)?.let{
                _todoList.value = it
            }
        }
    }

    fun updateTodo(todo: TodoData){
        viewModelScope.launch {
            todoRepository.updateTodo(todo)
        }
    }

    fun insertTodo(todo: TodoData){
        viewModelScope.launch {
            todoRepository.insertTodo(todo)
        }
    }

    fun updateDate(date: String){
        _todoList.value?.let{
           _todoList.value = it.copy(date = date)
        }
    }

    fun updateLevel(level: Int){
        _todoList.value?.let{
            _todoList.value = it.copy(level = level)
        }
    }

    fun updateTime(time: String){
        _todoList.value?.let{
            _todoList.value = it.copy(time = time)
        }
    }
}