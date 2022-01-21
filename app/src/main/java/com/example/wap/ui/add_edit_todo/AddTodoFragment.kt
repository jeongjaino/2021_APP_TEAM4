package com.example.wap.ui.add_edit_todo

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.wap.R
import com.example.wap.databinding.FragmentAddTodoBinding
import com.example.wap.ui.add_edit_todo.dialog.CalendarDialog
import com.example.wap.ui.add_edit_todo.dialog.TodoLevelDialog
import com.example.wap.model.todo.TodoData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoFragment : DialogFragment() {

    private val binding by lazy{ FragmentAddTodoBinding.inflate(layoutInflater)}

    lateinit var setTodoViewModel: SetTodoViewModel

    lateinit var addEditViewModel: AddEditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomFullDialog)
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
        dialog?.window?.setGravity(Gravity.BOTTOM)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setTodoViewModel = ViewModelProvider(requireActivity())[SetTodoViewModel::class.java]
        addEditViewModel = ViewModelProvider(requireActivity())[AddEditViewModel::class.java]

        binding.doneButton.setOnClickListener{
            if(binding.addTodo.text.isNotEmpty()){
                saveTodo()
            }
            else{
                Toast.makeText(context,"텍스트를 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }
        binding.calendarButton.setOnClickListener{
            showCalendarDialog()
        }
        binding.flagButton.setOnClickListener{
            showTodoLevelDialog()
        }

        setTodoViewModel.currentDrawable.observe(this){
            Glide.with(activity!!)
                .load(it)
                .into(binding.flagButton)
        }

        return binding.root
    }
    private fun saveTodo() {
        val todoText = binding.addTodo.text.toString()
        val date = setTodoViewModel.currentDate.value
        val time = setTodoViewModel.currentTime.value
        val drawable = setTodoViewModel.currentDrawable.value

        addEditViewModel.insertTodo(TodoData(todoText, date, time, drawable))
        binding.addTodo.text = null
        setTodoViewModel.initValues()

        Toast.makeText(requireContext(), "할 일이 추가되었습니다!", Toast.LENGTH_LONG).show()
    }

    private fun showCalendarDialog(){
        val dialog = CalendarDialog()
        dialog.show(activity!!.supportFragmentManager, "CalendarDialog")
    }
    private fun showTodoLevelDialog(){
        val dialog = TodoLevelDialog()
        dialog.setOnListener(object: TodoLevelDialog.TodoLevelDialogListener{
            override fun onLevelClick(drawable: Int) {
                Glide.with(activity!!)
                    .load(drawable)
                    .into(binding.flagButton)
                setTodoViewModel.setDrawable(drawable)
            }
        })
        dialog.show(activity!!.supportFragmentManager, "todo_level_dialog")
    }
}