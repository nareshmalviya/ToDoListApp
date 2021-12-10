package com.drc.todolistapp.ui.tasklist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.drc.todolistapp.model.Task
import com.drc.todolistapp.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: TaskRepository
): ViewModel() {



    val TaskList: MutableLiveData<List<Task>>

    init {
        TaskList =  MutableLiveData<List<Task>>()

    }

    suspend fun getTaskList(){

        TaskList.postValue( repository.getTasks())
    }


    suspend fun getTaskcount():Int{

       return ( repository.getTaskscount())
    }


}