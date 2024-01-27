package com.corbetta.minhastarefas.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.corbetta.minhastarefas.data.Task
import com.corbetta.minhastarefas.data.TasksRepository

class AddTaskViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    var taskUiState by mutableStateOf(TaskUiState())
        private set

    fun updateUiState(taskDetails: TaskDetails) {
        taskUiState =
            TaskUiState(taskDetails = taskDetails, isEntryValid = validateInput(taskDetails))
    }

    private fun validateInput(uiState: TaskDetails = taskUiState.taskDetails): Boolean {
        return with(uiState) {
            titulo.isNotBlank() && descricao.isNotBlank()
        }
    }


    suspend fun saveTask() {
        tasksRepository.insertItem(taskUiState.taskDetails.toTask())
    }
}

data class TaskUiState(
    val taskDetails: TaskDetails = TaskDetails(),
    val isEntryValid: Boolean =false
)

data class TaskDetails (
    val id: Int = 0,
    var titulo: String = "",
    var descricao: String = ""
)

fun TaskDetails.toTask(): Task = Task(
    id = id,
    titulo = titulo,
    descricao = descricao
)

fun Task.toTaskUiState(isEntryValid: Boolean = false): TaskUiState = TaskUiState(
    taskDetails = this.toTaskDetails(),
    isEntryValid = isEntryValid
)

fun Task.toTaskDetails(): TaskDetails = TaskDetails(
    id = id,
    titulo = titulo,
    descricao = descricao
)
