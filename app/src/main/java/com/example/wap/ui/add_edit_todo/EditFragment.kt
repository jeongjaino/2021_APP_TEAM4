package com.example.wap.ui.add_edit_todo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wap.databinding.FragmentEditBinding
import com.example.wap.model.todo.TodoData
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditFragment : DialogFragment() {

    private val binding by lazy{FragmentEditBinding.inflate(layoutInflater)}

    private val args by navArgs<EditFragmentArgs>()

    private lateinit var addEditViewModel: AddEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        addEditViewModel = ViewModelProvider(requireActivity())[AddEditViewModel::class.java]

        Log.d("Tag in Edit", args.position.toString())

        addEditViewModel.getTodoById(args.position)

        addEditViewModel.todoList.observe(this){
            it?.let{
                binding.todoEditText.setText(it.todo)
                binding.addEditDeadline.text = it.date
            }
        }

        binding.backButton.setOnClickListener{
            val direction: NavDirections = EditFragmentDirections.actionAddEditFragmentToListFragment()
            view!!.findNavController().navigate(direction)
        }

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        val text = binding.todoEditText.text.toString()
        val value = addEditViewModel.todoList.value!!
        if (text.isNotEmpty()) {
            addEditViewModel.updateTodo(TodoData(text,
                value.date, value.time, value.level, args.position))
        }
        else{
            addEditViewModel.updateTodo(TodoData(value.todo,
                value.date, value.time, value.level, args.position))
        }
    }
}