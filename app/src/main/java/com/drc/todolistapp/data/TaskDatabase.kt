package com.drc.todolistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.drc.todolistapp.model.Task


@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase: RoomDatabase() {

    abstract val taskDao: TaskDao


}