package com.example.wap.ui.completed_todo_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wap.model.completed.CompletedRepository
import com.example.wap.model.completed.CompletedTodo
import com.example.wap.model.todo.TodoData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CompletedViewModel @Inject constructor(
    private val completedRepository: CompletedRepository
): ViewModel(){

    private val _completedTodo = MutableLiveData<List<CompletedTodo>>()

    val completedTodo : LiveData<List<CompletedTodo>> = _completedTodo

    init{
        viewModelScope.launch {
            completedRepository.getCompletedTodos().collect { items ->
                _completedTodo.value = items
            }
        }
    }

    fun insertTodo(todo: CompletedTodo){
        viewModelScope.launch {
            completedRepository.insertCompletedTodo(todo)
        }
    }
}