package com.example.wap.ui.completed_todo_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wap.adapter.CompletedListAdapter
import com.example.wap.databinding.FragmentCompletedListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CompletedListFragment : Fragment() {

    private val completedViewModel: CompletedViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView

    private val binding by lazy{ FragmentCompletedListBinding.inflate(layoutInflater)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        connectRecyclerView()

        completedViewModel.completedTodo.observe(this){
            recyclerView.adapter = CompletedListAdapter(it)
        }

        binding.completedBackButton.setOnClickListener{
            val direction: NavDirections = CompletedListFragmentDirections.actionCompletedListFragmentToListFragment()
            findNavController().navigate(direction)
        }

        return binding.root
    }

    private fun connectRecyclerView(){
        recyclerView = binding.completedRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
    }
}