package com.example.wap.ui.todo_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wap.adapter.TodoListAdapter
import com.example.wap.databinding.FragmentTodoListBinding
import com.example.wap.model.todo.TodoData
import com.example.wap.model.completed.CompletedTodo
import com.example.wap.ui.add_edit_todo.AddTodoFragment
import com.example.wap.ui.completed_todo_list.CompletedViewModel
import com.example.wap.ui.character.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ListFragment : Fragment(), TodoListAdapter.OnCheckedChangeListener, TodoListAdapter.OnClickListener{

    private val binding by lazy{ FragmentTodoListBinding.inflate(layoutInflater)}
    private lateinit var recyclerView: RecyclerView

     lateinit var characterViewModel: CharacterViewModel

     lateinit var completedViewModel: CompletedViewModel

     private val todoListViewModel: TodoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        connectRecyclerView()

        characterViewModel = ViewModelProvider(requireActivity())[CharacterViewModel::class.java]
        completedViewModel = ViewModelProvider(this)[CompletedViewModel::class.java]

        todoListViewModel.todoList.observe(this){ value ->
            recyclerView.adapter = TodoListAdapter(this@ListFragment,this,value)
        }

        binding.addButton.setOnClickListener{
            //add
            val dialog = AddTodoFragment()
            dialog.show(activity!!.supportFragmentManager, "todo_level_dialog")
        }

        return binding.root
    }
    //checkBox check
    override fun onCheck(position: Int, isChecked: Boolean, todo: TodoData) {
        todoListViewModel.deleteTodo(todo)
        val completedTime = SimpleDateFormat("MM월 dd일").format(System.currentTimeMillis())
        completedViewModel.insertTodo(CompletedTodo(todo.todo, todo.date, todo.time, todo.level, completedTime))
        characterViewModel.updateLevel(todo.level!!)
    }

    override fun onCardClick(position: Int) {
        val directions: NavDirections = ListFragmentDirections.actionListFragmentToAddEditFragment(position = position)
        view!!.findNavController().navigate(directions)
        Log.d("tag in position",position.toString())
    }

    private fun connectRecyclerView() {
        recyclerView = binding.listRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
    }
}
