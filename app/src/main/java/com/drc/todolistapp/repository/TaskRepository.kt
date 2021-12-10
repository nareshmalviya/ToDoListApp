package com.drc.todolistapp.repository

import androidx.lifecycle.LiveData
import com.drc.todolistapp.data.TaskDao
import com.drc.todolistapp.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository@Inject constructor(private val dao: TaskDao)  {

     fun getTasks(): List<Task> {
        return dao.getTaskList()
    }

    fun getTaskscount(): Int {
        return dao.getTascount()
    }

     suspend fun insertTask(task: Task) {
        dao.addtask(task)
    }

     suspend fun updateTask(task: Task) {
        dao.update(task)
    }

    suspend fun deleteTask(task: Task) {
        dao.deletetask(task)
    }
}