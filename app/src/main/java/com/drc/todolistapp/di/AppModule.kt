package com.drc.todolistapp.di

import android.app.Application
import androidx.room.Room
import com.drc.todolistapp.Utils
import com.drc.todolistapp.data.TaskDao
import com.drc.todolistapp.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            Utils.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: TaskDatabase): TaskDao {
        return db.taskDao
    }


}