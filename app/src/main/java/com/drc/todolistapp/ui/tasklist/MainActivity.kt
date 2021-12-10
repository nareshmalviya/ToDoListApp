package com.drc.todolistapp.ui.tasklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.drc.todolistapp.databinding.ActivityMainBinding
import com.drc.todolistapp.model.Task
import com.drc.todolistapp.ui.addtask.AddTaskActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , TaskListAdapter.taskonlongclick{
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private lateinit var taskListAdapter: TaskListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.addtask.setOnClickListener {

            val intent = Intent( this ,AddTaskActivity::class.java)
            startActivity(intent)
        }


        taskListAdapter = TaskListAdapter()
        binding.taskList.layoutManager = GridLayoutManager(this,2)

        binding.taskList.adapter=taskListAdapter



    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Main).launch {

            val count :Int
            withContext(Dispatchers.Default){

                viewModel.getTaskList()
                 count =   viewModel.getTaskcount()


            }
            viewModel.TaskList.observe(this@MainActivity, Observer {

                taskListAdapter.setdata(it,this@MainActivity)
                taskListAdapter.notifyDataSetChanged()


                binding.taskprogress.setProgress(count*10)
            })
        }
    }

    override fun taskonlongclick(task: Task?) {
        val intent = Intent( this ,AddTaskActivity::class.java)
        val gson = Gson()
        val data = gson.toJson(task)
        intent.putExtra("task",data)
        startActivity(intent)
    }
}