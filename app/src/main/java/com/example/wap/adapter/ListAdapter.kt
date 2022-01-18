package com.example.wap.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.wap.R
import com.example.wap.databinding.ItemTodoListBinding
import com.example.wap.model.todo.TodoData

class ListAdapter(
    private val listener: OnCheckedChangeListener,
    private val clickListener: OnClickListener,
    private val dataset: List<TodoData>,
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
       val binding =
           ItemTodoListBinding.inflate(LayoutInflater.from(parent.context),parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val list = dataset[position]
        holder.setTodo(list)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class ListViewHolder(
        private val binding: ItemTodoListBinding
        ) : RecyclerView.ViewHolder(binding.root),
    CompoundButton.OnCheckedChangeListener, View.OnClickListener{

        lateinit var currentTodo: TodoData
        fun setTodo(todo: TodoData){
            binding.itemTodo.text = todo.toDo
            binding.itemDeadLine.text = todo.deadline
            this.currentTodo = todo
        }

        init{
            binding.itemCheckBox.setOnCheckedChangeListener(this)
            binding.todoItemCardView.setOnClickListener(this)
        }

        override fun onCheckedChanged(view: CompoundButton?, isChecked: Boolean) {
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                when(view?.id){
                    R.id.item_checkBox -> listener.onCheck(position, isChecked, currentTodo)
                }
            }
        }

        override fun onClick(view: View?) {
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                when(view?.id){
                    R.id.todo_item_cardView -> clickListener.onCardClick(position)
                }
                Log.d("tag adapter position", "${position}")
            }
        }
    }
    interface OnCheckedChangeListener{
        fun onCheck(position: Int, isChecked: Boolean, todo: TodoData)
    }
    interface OnClickListener{
        fun onCardClick(id: Int)
    }
}