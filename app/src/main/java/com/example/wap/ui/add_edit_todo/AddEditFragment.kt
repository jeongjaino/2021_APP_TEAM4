package com.example.wap.ui.add_edit_todo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wap.databinding.FragmentAddEditBinding
import com.example.wap.model.todo.TodoData
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddEditFragment : DialogFragment() {

    private val binding by lazy{FragmentAddEditBinding.inflate(layoutInflater)}

    private val args by navArgs<AddEditFragmentArgs>()

    private val addEditViewModel : AddEditViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        addEditViewModel.getTodoById(args.position)

        addEditViewModel.todoList.observe(this){
            it?.let{
                binding.todoEditText.setText(it.toDo)
                binding.addEditDeadline.text = it.deadline
            }
        }

        binding.backButton.setOnClickListener{

            addEditViewModel.updateTodo(TodoData(binding.todoEditText.text.toString(),
                addEditViewModel.todoList.value!!.deadline, false, args.position))

            val direction: NavDirections = AddEditFragmentDirections.actionAddEditFragmentToListFragment()
            view!!.findNavController().navigate(direction)
        }
        return binding.root
    }
}