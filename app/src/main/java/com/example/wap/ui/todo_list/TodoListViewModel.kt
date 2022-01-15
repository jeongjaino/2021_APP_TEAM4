package com.example.wap.ui.todo_list

import androidx.lifecycle.*
import com.example.wap.model.game.GameData
import com.example.wap.model.todo.TodoData
import com.example.wap.model.todo.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoRepository: TodoRepository
): ViewModel() {

    val todo = MutableLiveData<TodoData>()

    private val _todoList = MutableLiveData<List<TodoData>>()

    val todoList : LiveData<List<TodoData>> = _todoList

    init{
        viewModelScope.launch {
            todoRepository.getTodos().collect { items ->
                _todoList.value = items
            }
        }
    }

    fun insertTodo(todo: TodoData){
        viewModelScope.launch {
            todoRepository.insertTodo(todo)
        }
    }

    fun deleteTodo(todo: TodoData){
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
        }
    }
}