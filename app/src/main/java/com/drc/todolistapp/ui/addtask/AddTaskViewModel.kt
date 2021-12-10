package com.drc.todolistapp.ui.addtask

import androidx.lifecycle.ViewModel
import com.drc.todolistapp.model.Task
import com.drc.todolistapp.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val repository: TaskRepository
): ViewModel() {

    suspend fun addTask(task: Task){

        return repository.insertTask(task)
    }

    suspend fun updateTask(task: Task){

        return repository.insertTask(task)
    }
    suspend fun deleteTask(task: Task){

        return repository.deleteTask(task)
    }
}