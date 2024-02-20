package com.corbetta.minhastarefas.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corbetta.minhastarefas.data.Task
import com.corbetta.minhastarefas.data.TasksRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val tasksRepository: TasksRepository) : ViewModel() {



    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> =
        tasksRepository.getAllItemsStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
val taskDetails: TaskDetails = TaskDetails()

    suspend fun deleteItem (task: Task) {
        tasksRepository.deleteItem(task)
    }


}

data class HomeUiState(val taskList: List<Task> = listOf())



