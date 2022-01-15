package com.example.wap.ui.todo_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wap.R
import com.example.wap.adapter.ListAdapter
import com.example.wap.model.todo.TodoData
import com.example.wap.databinding.FragmentListBinding
import com.example.wap.ui.game.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.*

@AndroidEntryPoint
class ListFragment : Fragment(), ListAdapter.OnCheckedChangeListener, ListAdapter.OnClickListener{

    private val binding by lazy{ FragmentListBinding.inflate(layoutInflater)}
    private lateinit var recyclerView: RecyclerView

     lateinit var gameViewModel: GameViewModel

     private val todoListViewModel: TodoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        connectRecyclerView()

        gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]

        todoListViewModel.todoList.observe(this){ value ->
            recyclerView.adapter = ListAdapter(this@ListFragment,this,value)
        }

        binding.addButton.setOnClickListener{
            if(binding.addTodo.text.isNotEmpty() && binding.addDeadline.text.isNotEmpty()){
                val deadLine = binding.addDeadline.text.toString()
                val todoText = binding.addTodo.text.toString()
                val todoList = TodoData(todoText, deadLine, false)
                saveTodo(todoList)
            }
            else{
                Toast.makeText(context,"텍스트를 입력하세요",Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
    private fun saveTodo(todo: TodoData) {
        todoListViewModel.insertTodo(todo)
        Toast.makeText(requireContext(), "할 일이 추가되었습니다!", Toast.LENGTH_LONG).show()
        binding.addDeadline.text = null
        binding.addTodo.text = null
    }
    //checkBox check
    override fun onCheck(position: Int, isChecked: Boolean, todo: TodoData) {
        // gameViewModel.updateLevel()
        todoListViewModel.deleteTodo(todo)
        //투두 제거
        //완료 투두에 추가
    }

    override fun onCardClick(position: Int) {
        val directions: NavDirections = ListFragmentDirections.actionListFragmentToAddEditFragment(position = position)
        view!!.findNavController().navigate(directions)
    }

    private fun connectRecyclerView() {
        recyclerView = binding.listRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
    }
}