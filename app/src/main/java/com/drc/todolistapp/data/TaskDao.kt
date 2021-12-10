package com.drc.todolistapp.data

import androidx.room.*
import com.drc.todolistapp.model.Task
import kotlinx.coroutines.flow.Flow
import androidx.room.Update




@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getTaskList(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addtask(task: Task)

    @Query("SELECT COUNT(id) FROM task")
    fun getTascount():Int

    @Update
    fun update(task: Task?)

    @Delete
    suspend fun deletetask(task: Task)

}