package com.example.wap.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wap.databinding.ItemCompletedTodoListBinding
import com.example.wap.model.completed.CompletedTodo

class CompletedListAdapter(
    private val CompletedTodo: List<CompletedTodo>
): RecyclerView.Adapter<CompletedListAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemCompletedTodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val list = CompletedTodo[position]
        holder.setCompletedTodo(list)
    }

    override fun getItemCount(): Int {
        return CompletedTodo.size
    }

    class Holder(
        private val binding: ItemCompletedTodoListBinding
        ): RecyclerView.ViewHolder(binding.root)
    {
        fun setCompletedTodo(list: CompletedTodo){
            binding.completedItemTodo.text = list.toDo
            binding.completedItemTodo.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.completedItemDeadLine.text = list.date
            binding.completedTime.text = list.completedTime
        }

    }
}