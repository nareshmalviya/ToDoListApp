package com.drc.todolistapp.ui.tasklist

import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drc.todolistapp.R
import com.drc.todolistapp.databinding.TaskItemBinding
import com.drc.todolistapp.model.Task
import com.drc.todolistapp.ui.addtask.AddTaskActivity

class TaskListAdapter :RecyclerView.Adapter<TaskListAdapter.TasksHolder>() {

    private  var tasklist: List<Task>? = null
    private  var _context: MainActivity? = null


    fun setdata(postListX: List<Task>, contextX: MainActivity?){
        this.tasklist=postListX
        this._context=contextX
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksHolder = TasksHolder(TaskItemBinding
        .bind(LayoutInflater.from(parent.context).inflate(
            R.layout.task_item,
            parent,false)))

    override fun onBindViewHolder(holder: TasksHolder, position: Int) {

        holder.bind(tasklist?.get(position))
    }

    override fun getItemCount(): Int {
        if (tasklist==null)
            return 0
        return tasklist?.size!!
    }



    inner class TasksHolder(var binding: TaskItemBinding) :RecyclerView.ViewHolder(binding.root){

        fun bind(task: Task?){
            binding.taskdate.text=task?.date
            binding.tasktime.text=task?.time
            binding.taskdetail.text=task?.taskdetail
            task?.color?.let { binding.background.setBackgroundColor(_context!!.resources.getColor(it)) }

            binding.background.setOnLongClickListener {
                _context!!.taskonlongclick(task)
                return@setOnLongClickListener true
            }



        }
    }


    interface taskonlongclick{

       public fun  taskonlongclick(task: Task?)
    }
}