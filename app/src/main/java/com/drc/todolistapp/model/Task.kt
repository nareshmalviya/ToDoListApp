package com.drc.todolistapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task")
data class Task(

    @ColumnInfo(name = "taskdetail") var taskdetail: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "time") var time: String,
    @ColumnInfo(name = "color") var color: Int

){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}