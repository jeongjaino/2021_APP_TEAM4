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
import com.bumptech.glide.Glide
import com.example.wap.R
import com.example.wap.databinding.FragmentEditBinding
import com.example.wap.model.todo.TodoData
import com.example.wap.dialog.CalendarDialog
import com.example.wap.dialog.DialogViewModel
import com.example.wap.dialog.TodoLevelDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditFragment : DialogFragment() {

    private val binding by lazy{FragmentEditBinding.inflate(layoutInflater)}

    private val args by navArgs<EditFragmentArgs>()

    private lateinit var addEditViewModel: AddEditViewModel
    private lateinit var dialogViewModel: DialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        addEditViewModel = ViewModelProvider(requireActivity())[AddEditViewModel::class.java]
        dialogViewModel = ViewModelProvider(requireActivity())[DialogViewModel::class.java]

        addEditViewModel.getTodoById(args.position)

        addEditViewModel.todoList.observe(this){
            it?.let{
                binding.todoEditText.setText(it.todo)
                binding.editDeadline.text = it.date
                setFlag(it.level!!)
            }
        }

        dialogViewModel.currentDrawable.observe(this){
            setFlag(it)
            addEditViewModel.updateLevel(it)
        }

        dialogViewModel.currentDate.observe(this){
            binding.editDeadline.text = it
            addEditViewModel.updateDate(it)
        }

        dialogViewModel.currentTime.observe(this){
            addEditViewModel.updateTime(it)
        }

        binding.backButton.setOnClickListener{
            val direction: NavDirections = EditFragmentDirections.actionAddEditFragmentToListFragment()
            view!!.findNavController().navigate(direction)
        }

        binding.editTimeCardView.setOnClickListener{
            showCalendarDialog()
        }

        binding.editLevelCardView.setOnClickListener{
            showTodoLevelDialog()
        }
        return binding.root
    }

    private fun setFlag(drawable: Int){
        when (drawable) {
            R.drawable.yellow_flag -> {
                binding.editLevelText.text = "하"
            }
            R.drawable.green_flag -> {
                binding.editLevelText.text = "중"
            }
            else -> {
                binding.editLevelText.text = "상"
            }
        }

        Glide.with(activity!!)
            .load(drawable)
            .into(binding.editFlag)
    }

    private fun showCalendarDialog(){
        val dialog = CalendarDialog()
        dialog.show(activity!!.supportFragmentManager, "CalendarDialog")
    }

    private fun showTodoLevelDialog(){
        val dialog = TodoLevelDialog()
        dialog.show(activity!!.supportFragmentManager, "todo_level_dialog")
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
        dialogViewModel.initValues()
    }
}