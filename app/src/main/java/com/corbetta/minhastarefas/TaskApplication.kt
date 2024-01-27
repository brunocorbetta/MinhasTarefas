package com.corbetta.minhastarefas

import android.app.Application
import com.corbetta.minhastarefas.data.AppContainer
import com.corbetta.minhastarefas.data.AppDataContainer
import com.corbetta.minhastarefas.data.TasksRepository

class TaskApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}