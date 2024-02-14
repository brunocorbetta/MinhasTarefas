package com.corbetta.minhastarefas


import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.corbetta.minhastarefas.ui.AddTaskViewModel
import com.corbetta.minhastarefas.ui.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            AddTaskViewModel(
               taskApplication().container.tasksRepository
            )
        }

        initializer {
            HomeViewModel(taskApplication().container.tasksRepository)
        }
    }
}

fun CreationExtras.taskApplication(): TaskApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TaskApplication)