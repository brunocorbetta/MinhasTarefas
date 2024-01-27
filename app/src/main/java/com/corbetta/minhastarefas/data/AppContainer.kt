package com.corbetta.minhastarefas.data

import android.content.Context

interface AppContainer {
    val tasksRepository: TasksRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val tasksRepository: TasksRepository by lazy {
        OfflineTasksRepositoty(TaskDatabase.getDatabase(context).taskDao())
    }
}