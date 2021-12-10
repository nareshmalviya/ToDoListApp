package com.drc.todolistapp.ui.addtask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color.blue
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.drc.todolistapp.R
import com.drc.todolistapp.databinding.ActivityAddTaskBinding
import com.drc.todolistapp.model.Task
import com.drc.todolistapp.ui.tasklist.TaskListAdapter
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

@AndroidEntryPoint
class AddTaskActivity : AppCompatActivity()  {
    lateinit var binding: ActivityAddTaskBinding
    lateinit var viewModel: AddTaskViewModel
    lateinit var task :Task
    var isavenew:Boolean=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  =  ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)


        binding.selectdate.setOnClickListener { showdate() }
        binding.selecttime.setOnClickListener { showtime() }

        binding.saveanotes.setOnClickListener {

            if (binding.taskdetail.text.toString().isEmpty()){
                Toast.makeText(this,"Enter detail",Toast.LENGTH_LONG).show()
            }else if (binding.selectdate.text.equals("select date")){
                Toast.makeText(this,"Please Select Data",Toast.LENGTH_LONG).show()
            }else if (binding.selecttime.text.equals("select time")){
                Toast.makeText(this,"Please Select Time",Toast.LENGTH_LONG).show()
            }else{

                val selectedId: Int = binding.radiogroup.getCheckedRadioButtonId()
                var  color =0
                when (selectedId) {
                    R.id.blue -> color = R.color.light_blue
                    R.id.red ->color = R.color.light_red
                    R.id.orange -> color = R.color.light_orange
                    R.id.green -> color = R.color.light_green

                }

                Log.e("color",color.toString())

                if (isavenew){
                     task = Task(binding.taskdetail.text.toString(),binding.selectdate.text.toString(),binding
                        .selecttime.text.toString(),color)

                }else{
                    task.color = color
                    task.date = binding.selectdate.text.toString()
                    task.time = binding.selecttime.text.toString()
                    task.taskdetail = binding.taskdetail.text.toString()

                }


                GlobalScope.launch(Dispatchers.Main) {

                    withContext(Dispatchers.Default){
                        if (isavenew){
                            viewModel.addTask(task)
                        }else{
                            viewModel.updateTask(task)
                        }

                    }

                    finish()
                }


            }

        }


        if (!intent.getStringExtra("task").isNullOrEmpty()){

            val json = Gson()
            val data = json.fromJson(intent.getStringExtra("task"), Task::class.java)
            task=data
            binding.selectdate.text=data?.date
            binding.selecttime.text=data?.time
            binding.taskdetail.setText(data?.taskdetail)
            isavenew =false
        }

    }


    fun showdate(){

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear,
                                                                                          dayOfMonth ->
            binding.selectdate.text=("" + dayOfMonth + " " + monthOfYear + ", " + year)

        }, year, month, day)

        dpd.show()
    }


    fun showtime(){

        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)


        val tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener(function = { view, h, m ->

            binding.selecttime.text=  h.toString() + " : " + m +""

        }),hour,minute,false)

        tpd.show()
    }




}